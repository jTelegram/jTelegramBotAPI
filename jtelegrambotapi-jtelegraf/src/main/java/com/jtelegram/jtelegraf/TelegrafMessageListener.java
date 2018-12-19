package com.jtelegram.jtelegraf;

import com.jtelegram.api.message.Message;

/**
 * A listener for a {@link TelegrafMessageContext}, to be called when such a context is received.
 *
 * @author Nick Robson
 *
 * @param <ContextType> the type of the message context being listened to
 * @param <MessageType> the type of the message: e.g. TextMessage
 * @param <ContentsType> the type of the message contents: e.g. a String (for a TextMessage) or a Video (for a VideoMessage)
 */
public interface TelegrafMessageListener<ContextType extends TelegrafMessageContext<ContextType, MessageType, ContentsType>, MessageType extends Message<ContentsType>, ContentsType> {

    /**
     * Called when a message is received corresponding to this listener
     *
     * @param context the context of the message
     */
    void onMessage(TelegrafMessageContext<ContextType, MessageType, ContentsType> context);

}
