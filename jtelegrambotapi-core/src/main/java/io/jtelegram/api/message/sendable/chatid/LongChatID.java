package io.jtelegram.api.message.sendable.chatid;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LongChatID implements ChatID<Long> {
    private final long chatId;

    @Override
    public Long getID() {
        return chatId;
    }
}
