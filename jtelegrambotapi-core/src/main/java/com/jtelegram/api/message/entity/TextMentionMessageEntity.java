package com.jtelegram.api.message.entity;

import com.jtelegram.api.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TextMentionMessageEntity extends MessageEntity<TextMentionMessageEntity> {

    private User user;

}
