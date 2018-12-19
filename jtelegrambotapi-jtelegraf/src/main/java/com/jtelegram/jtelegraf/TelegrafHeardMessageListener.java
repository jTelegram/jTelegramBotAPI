package com.jtelegram.jtelegraf;

/**
 * A listener for a {@link TelegrafHeardMessageContext}, to be called when such a context is received.
 *
 * @author Nick Robson
 */
public interface TelegrafHeardMessageListener {

    /**
     * Called when a message is received corresponding to this listener
     *
     * @param context the context of the message
     */
    void onMessage(TelegrafHeardMessageContext context);

}
