package io.jtelegram.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jtelegram.api.request.BotRequestQueue;
import lombok.Builder;
import lombok.Getter;
import okhttp3.OkHttpClient;

/**
 * @author Mazen Kotb
 */
@Builder
public class TelegramBotRegistry {
    public static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    @Getter
    private String apiUrl = "https://api.telegram.org/bot";
    private OkHttpClient client = new OkHttpClient();
    private final BotRequestQueue requestQueue = new BotRequestQueue(client);

    public void setHttpClient(OkHttpClient client) {
        this.client = client;
        requestQueue.setClient(client);
    }
}
