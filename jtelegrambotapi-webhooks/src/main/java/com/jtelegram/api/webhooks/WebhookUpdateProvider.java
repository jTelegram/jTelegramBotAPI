package com.jtelegram.api.webhooks;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.TelegramBotRegistry;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.webhooks.SetWebhook;
import com.jtelegram.api.requests.webhooks.DeleteWebhook;
import com.jtelegram.api.update.PollingUpdateRunnable;
import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateProvider;
import com.jtelegram.api.update.UpdateType;
import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

@Getter
public class WebhookUpdateProvider implements UpdateProvider {
    private final Vertx vertx = Vertx.vertx();
    // all bots this webhook provider has ever seen
    private Set<String> allBots = new HashSet<>();
    private Map<String, TelegramBot> requestPaths = new ConcurrentHashMap<>();
    private HttpServer server;
    private String baseUrl;
    private volatile AsyncResult<HttpServer> bindingResult;
    private LocalInputFile selfSignedCertificate;
    private List<UpdateType> updateTypes;
    private Integer maxConnections;
    private Integer retryAfter;

    @Builder
    public WebhookUpdateProvider(HttpServerOptions serverOptions, File selfSignedCertificate,
                                 List<UpdateType> updateTypes, Integer maxConnections, Integer retryAfter) throws InterruptedException, FailBindingException {
        if (!serverOptions.isSsl()) {
            throw new IllegalArgumentException("Http Server must be SSL!");
        }

        if (selfSignedCertificate != null) {
            this.selfSignedCertificate = new LocalInputFile(selfSignedCertificate);
        }

        this.updateTypes = updateTypes;
        this.maxConnections = maxConnections;
        this.retryAfter = retryAfter;
        baseUrl = "https://" + serverOptions.getHost() + ":" + serverOptions.getPort() + "/";
        server = vertx.createHttpServer(serverOptions.setHost("0.0.0.0"));

        server.requestHandler((request) -> {
            String path = request.path().substring(1);

            if (requestPaths.containsKey(path)) {
                TelegramBot bot = requestPaths.get(path);

                request.bodyHandler((buffer) -> {
                    Update update = TelegramBotRegistry.GSON.fromJson(buffer.toString(), Update.class);
                    PollingUpdateRunnable.handleUpdate(bot, UpdateType.from(update.getClass()), update);
                });

                request.response().setStatusCode(200).end("OK");
                return;
            } else if (allBots.contains(path)) {
                request.response().setStatusCode(401).end("Bot no longer exists");
            }

            HttpServerResponse response = request.response()
                    .setStatusCode(404);

            if (retryAfter != null) {
                response.putHeader("Retry-After", String.valueOf(this.retryAfter));
            }

            response.end("No bot found on this endpoint");
        });

        CountDownLatch latch = new CountDownLatch(1);

        server.listen((res) -> {
            bindingResult = res;
            latch.countDown();
        });

        latch.await();

        if (bindingResult.failed()) {
            throw new FailBindingException(bindingResult.cause());
        }
    }

    @Override
    @SneakyThrows
    public void listenFor(TelegramBot bot) {
        bot.perform(SetWebhook.builder()
                .url(new URL(baseUrl + bot.getApiKey()))
                .allowedTypes(updateTypes)
                .certificate(selfSignedCertificate)
                .maxConnections(maxConnections)
                .callback(() -> {
                    requestPaths.put(bot.getApiKey(), bot);
                    allBots.add(bot.getApiKey());
                })
                .build());
    }

    @Override
    public void stopListening(TelegramBot bot) {
        requestPaths.remove(bot.getApiKey());
        bot.perform(DeleteWebhook.builder().build());
    }
}
