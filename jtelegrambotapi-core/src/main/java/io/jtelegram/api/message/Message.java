package io.jtelegram.api.message;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Message<T> {
    private int messageId;
    private User from;
    private long date;
    private Chat chat;
    private Message replyToMessage;
    private long editDate;
    /** CHANNEL FIELDS **/
    private String authorSignature;
    /** FORWARDING FIELDS **/
    private User forwardedFrom;
    private Chat forwardedFromChat;
    private int forwardedFromMessageId;
    private String forwardSignature;
    private long forwardDate;

    public abstract T getContent();

    public User getSender() {
        return from;
    }
}
