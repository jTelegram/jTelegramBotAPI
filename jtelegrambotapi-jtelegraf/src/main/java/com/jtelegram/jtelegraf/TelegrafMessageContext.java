package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.update.Update;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Getter;

/**
 * A wrapper around a message, for use with a {@link TelegrafBot}.
 *
 * @author Nick Robson
 *
 * @param <ContextType> the type of this context, essentially a "self-type", so that any filter operations can be
 *                       strongly typed according to the actual type of this update context
 * @param <MessageType> the type of the update: e.g. a MessageUpdate
 * @param <ContentsType> the type of the update contents: e.g. a Message
 */
@Getter
@Nonnull
@ParametersAreNonnullByDefault
public class TelegrafMessageContext<ContextType extends TelegrafMessageContext<ContextType, MessageType, ContentsType>, MessageType extends Message<ContentsType>, ContentsType> extends TelegrafUpdateContext<ContextType, Update.MessageUpdate, Message> {

    @Nonnull
    private final MessageType message;

    public TelegrafMessageContext(TelegramBot bot, Update.MessageUpdate update, MessageType message) {
        super(bot, update);
        this.message = message;
    }

    @Nonnull
    @Override
    public MessageType getContents() {
        // overridden to provide stronger typing
        return message;
    }

    @Nonnull
    public MessageType getMessage() {
        return message;
    }

    @Nullable
    public ContentsType getMessageContents() {
        return message.getContent();
    }

}
