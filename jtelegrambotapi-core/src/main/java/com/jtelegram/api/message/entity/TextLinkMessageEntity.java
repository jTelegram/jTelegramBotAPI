package com.jtelegram.api.message.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TextLinkMessageEntity extends MessageEntity<TextLinkMessageEntity> {

    private String url;

}
