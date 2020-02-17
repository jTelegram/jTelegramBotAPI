package com.jtelegram.api.ex.handler;

import com.jtelegram.api.ex.InvalidResponseException;
import com.jtelegram.api.ex.TelegramException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Logs exceptions with no further action.
 *
 * When sensitive is true, sensitive information
 * related to the exception will be printed.
 *
 * The sensitive flag is useful for debugging, but
 * should be set to false in production.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorLogger implements Consumer<TelegramException> {
    @Builder.Default
    private String identifier = "Generic";
    @Builder.Default
    private Logger logger = Logger.getGlobal();
    @Builder.Default
    private boolean sensitive = false;

    @Override
    public void accept(TelegramException e) {
        logger.severe(identifier + ": An error occurred during a Telegram API call, printing stacktrace...");

        if (sensitive) {
            if (e instanceof InvalidResponseException) {
                logger.severe("Full Message: " + ((InvalidResponseException) e).getSensitiveMessage());
            }
        }

        e.printStackTrace();
    }
}
