package com.jtelegram.api.chat;

import lombok.Getter;

public enum ChatType {
    PRIVATE(PrivateChat.class),
    GROUP(GroupChat.class),
    SUPERGROUP(SupergroupChat.class),
    CHANNEL(ChannelChat.class);

    @Getter
    private Class<? extends Chat> representingClass;

    ChatType(Class<? extends Chat> representingClass) {
        this.representingClass = representingClass;
    }
}
