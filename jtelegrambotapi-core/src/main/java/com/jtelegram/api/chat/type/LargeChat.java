package com.jtelegram.api.chat.type;

import com.jtelegram.api.message.Message;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class LargeChat extends MultiMemberChat {
    private String description;
    private String inviteLink;
    private Message pinnedMessage;
}
