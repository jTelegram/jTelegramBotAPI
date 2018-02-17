package com.jtelegram.api.message.impl.service;

import lombok.Getter;
import lombok.ToString;

/**
 * When a user logs in using a login widget,
 * the bot is given permission to chat with
 * them. This message is sent as a notification
 * that this has happened.
 */
@Getter
@ToString
public class UserLoggedInMessage extends ServiceMessage {
    /**
     * The domain of the website on which the user logged in
     */
    private String connectedWebsite;
}
