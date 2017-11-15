package io.jtelegram.api.update;

import io.jtelegram.api.TelegramBot;

/**
 * Listens for requests from Telegram's servers
 * for one or more bots. The provider is owned
 * by a registry and should call the registerUpdate()
 * method in the bot registry when an update is received
 *
 * The update provider is expected to be given
 * the owning registry in the constructor to
 * do so.
 *
 * @author Mazen Kotb
 */
public interface UpdateProvider {
    /**
     * Begin listening for updates for this bot
     * @param bot
     */
    void listenFor(TelegramBot bot);

    /**
     * Stop listening for updates for this bot
     * @param bot
     */
    void stopListening(TelegramBot bot);
}
