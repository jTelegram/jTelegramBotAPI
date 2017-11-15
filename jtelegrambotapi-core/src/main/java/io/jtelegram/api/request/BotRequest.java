package io.jtelegram.api.request;

import io.jtelegram.api.TelegramBot;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Useful pair for when inserted into the request queue
 *
 * @author Mazen Kotb
 */
@AllArgsConstructor
@Getter
public class BotRequest {
    private TelegramBot bot;
    private TelegramRequest request;
}
