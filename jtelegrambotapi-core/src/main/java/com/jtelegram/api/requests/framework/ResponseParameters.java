package com.jtelegram.api.requests.framework;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseParameters {
    // in the case that the group the bot
    // is trying to send a message to has
    // upgraded to a supergroup, the new
    // chat id provided should be used
    // instead
    // TODO find a way to handle this
    private long migrateToChatId;
    // if the bot has been spamming the
    // telegram server, this is the time
    // in seconds the bot should wait until
    // it makes its next request
    private int retryAfter = -1;
}
