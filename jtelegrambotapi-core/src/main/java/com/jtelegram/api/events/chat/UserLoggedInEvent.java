package com.jtelegram.api.events.chat;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.service.UserLoggedInMessage;

/**
 * When the user logs in from a website
 * (and most of the time, a new chat is instantiated)
 *
 * At this point, it is guaranteed that the bot can
 * send messages to the user.
 */
public class UserLoggedInEvent extends ServiceMessageEvent<UserLoggedInMessage> {
    public UserLoggedInEvent(TelegramBot bot, UserLoggedInMessage originMessage) {
        super(bot, originMessage);
    }
}
