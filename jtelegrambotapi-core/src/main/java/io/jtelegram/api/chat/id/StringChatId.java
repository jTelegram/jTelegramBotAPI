package io.jtelegram.api.chat.id;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringChatId implements ChatId<String> {
    private final String chatId;
    //TODO: Explain in javadocs that the username HAS an @

    @Override
    public String getId() {
        return chatId;
    }

    @Override
    public String toString() {
        return chatId;
    }
}
