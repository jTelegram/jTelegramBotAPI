package io.jtelegram.api.webhooks;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.TelegramBotRegistry;
import io.jtelegram.api.message.input.file.LocalInputFile;
import io.jtelegram.api.requests.webhooks.DeleteWebhook;
import io.jtelegram.api.requests.webhooks.SetWebhook;
import io.jtelegram.api.update.PollingUpdateRunnable;
import io.jtelegram.api.update.Update;
import io.jtelegram.api.update.UpdateProvider;
import io.jtelegram.api.update.UpdateType;
import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

@Getter
public class WebhookUpdateProvider implements UpdateProvider {
    private final Vertx vertx = Vertx.vertx();
    private Map<String, TelegramBot> requestPaths = new ConcurrentHashMap<>();
    private HttpServer server;
    private String baseUrl;
    private volatile AsyncResult<HttpServer> bindingResult;
    private LocalInputFile selfSignedCertificate;
    private List<UpdateType> updateTypes;
    private Integer maxConnections;

    @Builder
    public WebhookUpdateProvider(HttpServerOptions serverOptions, File selfSignedCertificate,
                                 List<UpdateType> updateTypes, Integer maxConnections) throws InterruptedException, FailBindingException {
        if (!serverOptions.isSsl()) {
            throw new IllegalArgumentException("Http Server must be SSL!");
        }

        if (selfSignedCertificate != null) {
            this.selfSignedCertificate = new LocalInputFile(selfSignedCertificate);
        }

        this.updateTypes = updateTypes;
        this.maxConnections = maxConnections;
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
            }

            request.response().setStatusCode(404).end("No bot found on this endpoint");
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
                .callback(() -> requestPaths.put(bot.getApiKey(), bot))
                .build());
    }

    @Override
    public void stopListening(TelegramBot bot) {
        requestPaths.remove(bot.getApiKey());
        bot.perform(DeleteWebhook.builder().build());
    }
}
