package com.jtelegram.jtelegraf;

/**
 * A listener for a {@link TelegrafCommandContext}, to be called when such a context is received.
 *
 * @author Nick Robson
 */
public interface TelegrafCommandListener {

    /**
     * Called when a command is received corresponding to this listener
     *
     * @param context the context of the command
     */
    void onCommand(TelegrafCommandContext context);

}
