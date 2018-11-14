package com.jtelegram.api.message.impl.service;

import com.jtelegram.api.message.Message;
import lombok.ToString;

/**
 * Sharable class between all service messages
 */
@ToString(callSuper = true)
public abstract class ServiceMessage extends Message<Object> {
    @Override
    public Object getContent() {
        return null;
    }
}
