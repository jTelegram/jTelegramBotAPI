package com.jtelegram.api.message.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public enum MessageEntityType {
    @SerializedName("mention")
    MENTION,
    @SerializedName("hashtag")
    HASHTAG,
    @SerializedName("bot_command")
    BOT_COMMAND,
    @SerializedName("url")
    URL,
    @SerializedName("email")
    EMAIL,
    @SerializedName("bold")
    BOLD,
    @SerializedName("italic")
    ITALIC,
    @SerializedName("code")
    CODE,
    @SerializedName("pre")
    PRE,
    @SerializedName("text_link")
    TEXT_LINK(TextLinkMessageEntity.class),
    @SerializedName("text_mention")
    TEXT_MENTION(TextMentionMessageEntity.class),
    @SerializedName("@@unsupported_by_api@@")
    UNSUPPORTED(UnsupportedMessageEntity.class);

    private Class<? extends MessageEntity> implementationClass;

    MessageEntityType() {
        this.implementationClass = MessageEntity.DefaultMessageEntity.class;
    }

    MessageEntityType(Class<? extends MessageEntity> implementationClass) {
        this.implementationClass = implementationClass;
    }
}
