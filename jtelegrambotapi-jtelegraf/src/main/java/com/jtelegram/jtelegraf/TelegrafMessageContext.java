package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.update.Update;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Getter;

/**
 * @author Nick Robson
 */
@Getter
@Nonnull
@ParametersAreNonnullByDefault
public class TelegrafMessageContext<MessageContext extends TelegrafMessageContext<MessageContext, C, M>, C, M extends Message<C>> extends TelegrafUpdateContext<MessageContext, Message, Update.MessageUpdate> {

    @Nonnull
    private final M message;

    public TelegrafMessageContext(TelegramBot bot, Update.MessageUpdate update, M message) {
        super(bot, update);
        this.message = message;
    }

    @Nonnull
    @Override
    public M getContents() {
        // overridden to provide stronger typing
        return message;
    }

    @Nonnull
    public M getMessage() {
        return message;
    }

    @Nullable
    public C getMessageContents() {
        return message.getContent();
    }

}
