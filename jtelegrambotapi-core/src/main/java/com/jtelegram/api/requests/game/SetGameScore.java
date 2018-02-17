package com.jtelegram.api.requests.game;

import com.jtelegram.api.requests.framework.QueryTelegramRequest;
import com.jtelegram.api.chat.id.LongChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class SetGameScore extends QueryTelegramRequest<Message> {
    private Long userId;
    private Integer score;
    private Boolean force;
    private Boolean disableEditMessage;
    private LongChatId chatId;
    private Integer messageId;
    private String inlineMessageId;

    @Builder
    private SetGameScore(Consumer<Message> callback, Consumer<TelegramException> errorHandler, Long userId,
                        Integer score, Boolean force, Boolean disableEditMessage, LongChatId chatId, Integer messageId,
                        String inlineMessageId) {
        super("setGameScore", Message.class, callback, errorHandler);
        this.userId = userId;
        this.score = score;
        this.force = force;
        this.disableEditMessage = disableEditMessage;
        this.chatId = chatId;
        this.messageId = messageId;
        this.inlineMessageId = inlineMessageId;
    }

    @Override
    protected boolean isValid() {
        return userId != null && score != null && score > 0 && (inlineMessageId != null || (chatId != null && messageId != null));
    }
}
