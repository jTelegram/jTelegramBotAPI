package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.update.Update;
import java.util.regex.Matcher;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Getter;

/**
 * @author Nick Robson
 */
@Getter
@ParametersAreNonnullByDefault
public class TelegrafHeardMessageContext extends TelegrafMessageContext<TelegrafHeardMessageContext, String, TextMessage> {

    @Nonnull
    private final Matcher matcher;

    public TelegrafHeardMessageContext(TelegramBot bot, Update.MessageUpdate update, TextMessage message, Matcher matcher) {
        super(bot, update, message);
        this.matcher = matcher;
    }

}
