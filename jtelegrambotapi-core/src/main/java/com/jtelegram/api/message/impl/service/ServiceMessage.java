package com.jtelegram.api.message.impl.service;

import com.jtelegram.api.message.Message;

/**
 * Sharable class between all service messages
 */
public abstract class ServiceMessage extends Message {
    @Override
    public Object getContent() {
        return null;
    }
}
