package io.jtelegram.api.message.sendable.chatid;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringChatID implements ChatID<String> {
    private final String chatID;
    //TODO: Explain in javadocs that the username HAS an @
    @Override
    public String getID() {
        return chatID;
    }
}
