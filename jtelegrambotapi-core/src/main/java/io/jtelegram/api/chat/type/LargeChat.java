package io.jtelegram.api.chat.type;

import io.jtelegram.api.message.Message;
import lombok.Getter;

@Getter
public class LargeChat extends MultiMemberChat {
    private String description;
    private String inviteLink;
    private Message pinnedMessage;
}
