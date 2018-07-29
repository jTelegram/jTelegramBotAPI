package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.ContactMessage;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.SendableMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class SendContact extends SendableMessageRequest<ContactMessage> {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String vcard;

    @Builder
    private SendContact(Consumer<ContactMessage> callback, Consumer<TelegramException> errorHandler,
                        ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup,
                        String phoneNumber, String firstName, String lastName, String vcard) {
        super("sendContact", ContactMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.vcard = vcard;
    }
}
