package com.jtelegram.api;

import com.jtelegram.api.commands.CommandRegistry;
import com.jtelegram.api.events.EventRegistry;
import com.jtelegram.api.requests.framework.BotRequest;
import com.jtelegram.api.requests.framework.BotRequestQueue;
import com.jtelegram.api.requests.framework.TelegramRequest;
import com.jtelegram.api.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Getter
@EqualsAndHashCode(of = {"apiKey", "botInfo"})
public class TelegramBot {
    private final BotRequestQueue requestQueue;
    private final EventRegistry eventRegistry;
    private final CommandRegistry commandRegistry;

    private TelegramBotRegistry registry;
    private String apiKey;
    @Setter
    private User botInfo;

    TelegramBot(TelegramBotRegistry registry, String apiKey) {
        this.registry = registry;
        this.apiKey = apiKey;
        this.eventRegistry = new EventRegistry(this);
        this.commandRegistry = new CommandRegistry(this);
        this.requestQueue = new BotRequestQueue(registry.getClient());
        requestQueue.start();
    }

    public void perform(TelegramRequest request) {
        requestQueue.getRequestQueue().add(new BotRequest(this, request));
    }

    public InputStream downloadFile(TelegramFile file) throws IOException {
        return downloadFile(file.getFilePath());
    }

    public InputStream downloadFile(String filePath) throws IOException {
        URI uri = URI.create(registry.getFileApiUrl() + apiKey + "/" + filePath);
        HttpResponse<InputStream> body;
        try {
            body = registry.getClient().send(
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .GET().build(), HttpResponse.BodyHandlers.ofInputStream());
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        return body == null ? null : body.body();
    }
}
