package com.jtelegram.jtelegraf;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.update.Update;
import javax.annotation.Nonnull;
import lombok.Getter;

/**
 * @author Nick Robson
 */
@Getter
public class TelegrafMessageContext<C, M extends Message<C>> extends TelegrafUpdateContext<Update.MessageUpdate, Message> {

    private final M message;

    public TelegrafMessageContext(@Nonnull Update.MessageUpdate update, @Nonnull M message) {
        super(update);
        this.message = message;
    }

    @Nonnull
    @Override
    public M getContents() {
        // overridden to provide stronger typing
        return message;
    }

    public M getMessage() {
        return message;
    }

    public C getMessageContents() {
        return message.getContent();
    }

}
