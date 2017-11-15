package io.jtelegram.api.chat;

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
    private User forwardFrom;
    private Message replyToMessage;
    private long editDate;
}
