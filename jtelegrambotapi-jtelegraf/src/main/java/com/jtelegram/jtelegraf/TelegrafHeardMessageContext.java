package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.update.Update;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Getter;

/**
 * A wrapper around a message, for use with {@link TelegrafBot#hears(Pattern, TelegrafHeardMessageListener)}.
 *
 * @author Nick Robson
 */
@Getter
@ParametersAreNonnullByDefault
public class TelegrafHeardMessageContext extends TelegrafMessageContext<TelegrafHeardMessageContext, TextMessage, String> {

    /**
     * The matcher instance containing matches for the pattern. <br>
     * <i>Note</i>: <b>The matcher ALREADY contains the first match.</b> <br>
     * Use a {@code do-while} loop if you need to get all matches.
     */
    @Nonnull
    private final Matcher matcher;

    public TelegrafHeardMessageContext(TelegramBot bot, Update.MessageUpdate update, TextMessage message, Matcher matcher) {
        super(bot, update, message);
        this.matcher = matcher;
    }

}
