package io.jtelegram.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jtelegram.api.chat.*;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.inline.keyboard.InlineKeyboardRow;
import io.jtelegram.api.inline.result.framework.InlineResultType;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.TextMessage;
import io.jtelegram.api.message.input.file.InputFile;
import io.jtelegram.api.message.input.media.InputMediaType;
import io.jtelegram.api.message.keyboard.ReplyKeyboardRow;
import io.jtelegram.api.message.sticker.MaskPoint;
import io.jtelegram.api.requests.GetMe;
import io.jtelegram.api.update.*;
import io.jtelegram.api.util.LowercaseEnumAdapter;
import lombok.Builder;
import lombok.Getter;
import okhttp3.OkHttpClient;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

@Getter
public class TelegramBotRegistry {
    public static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
            .registerTypeAdapter(UpdateType.class, new UpdateType.GsonTypeAdapter())
            .registerTypeAdapter(ChatType.class, new LowercaseEnumAdapter<>(ChatType.class))
            .registerTypeAdapter(MaskPoint.class, new LowercaseEnumAdapter<>(MaskPoint.class))
            .registerTypeAdapter(InputMediaType.class, new LowercaseEnumAdapter<>(InputMediaType.class))
            .registerTypeAdapter(ChatAction.class, new LowercaseEnumAdapter<>(ChatAction.class))
            .registerTypeAdapter(ChatMemberStatus.class, new LowercaseEnumAdapter<>(ChatMemberStatus.class))
            .registerTypeAdapter(InlineResultType.class, new LowercaseEnumAdapter<>(InlineResultType.class))
            .registerTypeAdapter(InlineKeyboardRow.class, new InlineKeyboardRow.Serializer())
            .registerTypeAdapter(ReplyKeyboardRow.class, new ReplyKeyboardRow.Serializer())
            .registerTypeAdapter(Update.class, new Update.Deserializer())
            .registerTypeAdapter(Chat.class, new Chat.Deserializer())
            .registerTypeAdapter(Message.class, new Message.Deserializer())
            .registerTypeHierarchyAdapter(InputFile.class, new InputFile.Serializer())
            .registerTypeHierarchyAdapter(ChatId.class, new ChatId.Serializer())
            .create();
    private final UpdateProvider updateProvider;
    private String apiUrl = "https://api.telegram.org/bot";
    private OkHttpClient client = new OkHttpClient();
    private final Set<TelegramBot> bots = new HashSet<>();

    @Builder
    private TelegramBotRegistry(UpdateProvider updateProvider, String apiUrl, OkHttpClient client) {
        this.updateProvider = updateProvider;

        if (apiUrl != null) {
            this.apiUrl = apiUrl;
        }

        if (client != null) {
            this.client = client;
        }
    }

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
                    bot.setBotInfo(user);
                    callback.accept(bot, null);
                    bots.add(bot);
                    updateProvider.listenFor(bot);
                })
                .errorHandler((error) -> callback.accept(null, error))
                .build()
        );
    }
}
