package com.jtelegram.api.requests.framework;

import com.jtelegram.api.TelegramBot;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Useful pair for when inserted into the request queue
 */
@AllArgsConstructor
@Getter
public class BotRequest {
    private TelegramBot bot;
    private TelegramRequest request;
}
