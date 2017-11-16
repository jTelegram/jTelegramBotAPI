package io.jtelegram.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.GetMe;
import io.jtelegram.api.update.*;
import lombok.Builder;
import lombok.Getter;
import okhttp3.OkHttpClient;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Mazen Kotb
 */
@Builder
@Getter
public class TelegramBotRegistry {
    public static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .registerTypeAdapter(UpdateType.class, new UpdateTypeAdapter())
            .registerTypeAdapter(Update.class, new UpdateDeserializer())
            .create();
    private final UpdateProvider updateProvider;
    private String apiUrl = "https://api.telegram.org/bot";
    private OkHttpClient client = new OkHttpClient();
    private final Set<TelegramBot> bots = new HashSet<>();

    public void setHttpClient(OkHttpClient client) {
        this.client = client;
        bots.forEach((bot) -> bot.getRequestQueue().setClient(client));
    }

    /**
     * Registers the bot and makes a request to telegram to verify the apiKey is correct
     * This action will be performed on the bot's dedicated request queue, and thus a
     * callback must be used. The callback takes a similar approach of a promise in JS.
     *
     * If the verification was successful, the bot will be non-null and the exception will
     * be null and vice-versa.
     *
     * @param apiKey The Telegram bot's API Key
     * @param callback Callback
     */
    public void registerBot(String apiKey, BiConsumer<TelegramBot, TelegramException> callback) {
        TelegramBot bot = new TelegramBot(this, apiKey);

        bot.perform(GetMe.builder()
                .callback((user) -> {
                    callback.accept(bot, null);
                    bots.add(bot);
                    updateProvider.listenFor(bot);
                })
                .errorHandler((error) -> callback.accept(null, error))
                .build()
        );
    }
}