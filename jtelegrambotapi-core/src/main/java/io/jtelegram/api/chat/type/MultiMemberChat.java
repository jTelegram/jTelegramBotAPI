package io.jtelegram.api.chat.type;

import io.jtelegram.api.chat.Chat;
import lombok.Getter;

@Getter
public class MultiMemberChat extends Chat {
    private String title;
    private boolean allMembersAreAdministrators;
}
