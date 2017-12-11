package com.jtelegram.api.update;

import com.jtelegram.api.TelegramBot;

/**
 * Listens for requests from Telegram's servers
 * for one or more bots. The provider is owned
 * by a registry and should call the registerUpdate()
 * method in the bot registry when an update is received
 *
 * The update provider is expected to be given
 * the owning registry in the constructor to
 * do so.
 */
public interface UpdateProvider {
    /**
     * Begin listening for updates for this bot
     * @param bot The bot instance
     */
    void listenFor(TelegramBot bot);

    /**
     * Stop listening for updates for this bot
     * @param bot The bot instance
     */
    void stopListening(TelegramBot bot);
}
