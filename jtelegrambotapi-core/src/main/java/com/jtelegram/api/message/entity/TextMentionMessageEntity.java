package com.jtelegram.api.message.entity;

import com.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class TextMentionMessageEntity extends MessageEntity {
    private User user;
}
