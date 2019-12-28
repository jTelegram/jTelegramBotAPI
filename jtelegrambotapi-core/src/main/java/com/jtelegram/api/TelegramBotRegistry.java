package com.jtelegram.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.ChatAction;
import com.jtelegram.api.chat.ChatMemberStatus;
import com.jtelegram.api.chat.ChatType;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.inline.keyboard.InlineKeyboardRow;
import com.jtelegram.api.inline.result.framework.InlineResultType;
import com.jtelegram.api.message.entity.MessageEntity;
import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateProvider;
import com.jtelegram.api.update.UpdateType;
import com.jtelegram.api.util.LowercaseEnumAdapter;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.media.InputMediaType;
import com.jtelegram.api.message.keyboard.ReplyKeyboardRow;
import com.jtelegram.api.message.sticker.MaskPoint;
import com.jtelegram.api.requests.GetMe;
import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Modifier;
import java.net.http.HttpClient;
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
            .registerTypeAdapter(MessageEntity.class, new MessageEntity.Deserializer())
            .registerTypeHierarchyAdapter(InputFile.class, new InputFile.Serializer())
            .registerTypeHierarchyAdapter(ChatId.class, new ChatId.Serializer())
            .create();
    private final UpdateProvider updateProvider;
    private String apiUrl = "https://api.telegram.org/bot";
    private String fileApiUrl = "https://api.telegram.org/file/bot";
    private HttpClient client = HttpClient.newHttpClient();
    // <1 is a dynamic thread pool
    // 1 is a single thread pool
    // >1 is a multi thread pool
    private int eventThreadCount = 1;
    private final Set<TelegramBot> bots = new HashSet<>();

    @Builder
    private TelegramBotRegistry(UpdateProvider updateProvider, String apiUrl, HttpClient client, Integer eventThreadCount) {
        this.updateProvider = updateProvider;

        if (apiUrl != null) {
            this.apiUrl = apiUrl;
        }

        if (client != null) {
            this.client = client;
        }

        if (eventThreadCount != null) {
            this.eventThreadCount = eventThreadCount;
        }
    }

    public void setHttpClient(HttpClient client) {
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
