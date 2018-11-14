package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.Message;

/**
 * @author Nick Robson
 */
public interface TelegrafMessageListener<C, T extends Message<C>> {

    void onUpdate(TelegrafMessageContext<C, T> context, TelegramBot bot);

}
