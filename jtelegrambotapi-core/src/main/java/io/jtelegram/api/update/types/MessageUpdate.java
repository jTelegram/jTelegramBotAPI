package io.jtelegram.api.update.types;

import io.jtelegram.api.chat.message.Message;
import io.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * When a new message is being received
 */
@Getter
public class MessageUpdate extends Update {
    private Message message;
}
