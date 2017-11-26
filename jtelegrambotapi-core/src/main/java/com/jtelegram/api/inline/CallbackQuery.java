package com.jtelegram.api.inline;

import com.jtelegram.api.user.User;
import com.jtelegram.api.message.Message;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CallbackQuery {
    private String id;
    private User from;
    private Message message;
    private String inlineMessageId;
    private String chatInstance;
    private String data;
    private String gameShortName;
}
