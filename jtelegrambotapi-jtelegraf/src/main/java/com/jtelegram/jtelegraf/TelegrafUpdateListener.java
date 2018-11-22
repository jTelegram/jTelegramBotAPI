package com.jtelegram.jtelegraf;

import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateContents;

/**
 * @author Nick Robson
 */
public interface TelegrafUpdateListener<Self extends TelegrafUpdateContext<Self, U, T>, U extends UpdateContents, T extends Update<U>> {

    void onUpdate(TelegrafUpdateContext<Self, U, T> context);

}
