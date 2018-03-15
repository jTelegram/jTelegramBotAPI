package com.jtelegram.api.chat.type;

import com.jtelegram.api.chat.Chat;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class MultiMemberChat extends Chat {
    private String title;
    private boolean allMembersAreAdministrators;
}
