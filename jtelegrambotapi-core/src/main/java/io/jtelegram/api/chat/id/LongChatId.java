package io.jtelegram.api.chat.id;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LongChatId implements ChatId<Long> {
    private final long chatId;

    @Override
    public Long getId() {
        return chatId;
    }
}
