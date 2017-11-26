package com.jtelegram.api.requests.message.send;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.requests.message.framework.req.SendableMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class SendGame extends SendableMessageRequest<Message> {
    @SerializedName("game_short_name")
    private String gameId;

    @Builder
    private SendGame(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId,
                     Boolean disableNotification, InlineKeyboardMarkup replyMarkup, String gameId) {
        super("sendGame", Message.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.gameId = gameId;
    }
}
