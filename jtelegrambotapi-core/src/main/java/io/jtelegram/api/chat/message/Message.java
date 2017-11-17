package io.jtelegram.api.chat.message;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.user.User;
import lombok.Getter;

/**
 * Right now just a filler with basics
 * until a proper design is thought out
 */
@Getter
public abstract class Message<T> {
    private int messageId;
    private User from;
    private long date;
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
}
