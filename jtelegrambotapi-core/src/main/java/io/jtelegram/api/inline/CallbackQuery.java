package io.jtelegram.api.inline;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.user.User;
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
