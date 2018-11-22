package com.jtelegram.jtelegraf;

import com.jtelegram.api.message.Message;

/**
 * @author Nick Robson
 */
public interface TelegrafMessageListener<MessageContext extends TelegrafMessageContext<MessageContext, C, T>, C, T extends Message<C>> {

    void onMessage(TelegrafMessageContext<MessageContext, C, T> context);

}
