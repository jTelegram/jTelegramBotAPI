package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateContents;

/**
 * @author Nick Robson
 */
public interface TelegrafUpdateListener<T extends Update<U>, U extends UpdateContents> {

    void onUpdate(TelegrafUpdateContext<T, U> context, TelegramBot bot);

}
