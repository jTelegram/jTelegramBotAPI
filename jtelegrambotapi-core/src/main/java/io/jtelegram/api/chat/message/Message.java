package io.jtelegram.api.chat.message;

import io.jtelegram.api.user.User;
import lombok.Getter;

/**
 * Right now just a filler with basics
 * until a proper design is thought out
 */
@Getter
public class Message {
    private int messageId;
    private User from;
    private long date;
    private Message replyToMessage;
    private long editDate;
    private User forwardedFrom;

}
