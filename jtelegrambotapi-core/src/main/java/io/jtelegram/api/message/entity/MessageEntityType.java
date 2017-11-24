package io.jtelegram.api.message.entity;

import com.google.gson.annotations.SerializedName;

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
    TEXT_LINK,
    @SerializedName("text_mention")
    TEXT_MENTION
}
