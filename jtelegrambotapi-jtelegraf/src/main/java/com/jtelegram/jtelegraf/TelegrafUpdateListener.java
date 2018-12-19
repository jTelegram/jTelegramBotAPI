package com.jtelegram.jtelegraf;

import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateContents;

/**
 * A listener for a {@link TelegrafUpdateContext}, to be called when such a context is received.
 *
 * @author Nick Robson
 *
 * @param <ContextType> the type of the update context being listened to
 * @param <UpdateType> the type of the update: e.g. MessageUpdate
 * @param <ContentsType> the type of the update contents: e.g. a Message
 */
public interface TelegrafUpdateListener<ContextType extends TelegrafUpdateContext<ContextType, UpdateType, ContentsType>, UpdateType extends Update<ContentsType>, ContentsType extends UpdateContents> {

    /**
     * Called when an update is received corresponding to this listener
     *
     * @param context the context of the update
     */
    void onUpdate(TelegrafUpdateContext<ContextType, UpdateType, ContentsType> context);

}
