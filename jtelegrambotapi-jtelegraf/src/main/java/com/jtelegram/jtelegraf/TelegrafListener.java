package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateContents;

/**
 * @author Nick Robson
 */
public interface TelegrafListener<T extends Update<U>, U extends UpdateContents> {

    void onUpdate(TelegrafContext<T, U> context, TelegramBot bot);

}
