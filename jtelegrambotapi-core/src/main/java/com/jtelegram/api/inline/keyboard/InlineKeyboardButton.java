package com.jtelegram.api.inline.keyboard;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.message.games.CallbackGame;
import lombok.*;

@Getter
@ToString
@Builder
public class InlineKeyboardButton {
    @SerializedName("text")
    private String label;
    private String url;
    private String callbackData;
    private String switchInlineQuery;
    private String switchInlineQueryCurrentChat;
    private CallbackGame callbackGame;
    private Boolean pay;
}
