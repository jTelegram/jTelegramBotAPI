package com.jtelegram.api.commands.filters;

import com.jtelegram.api.chat.ChatType;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;

/**
 * A {@link CommandFilter} testing if the bot was mentioned in
 * the command.
 *
 * @author Nick Robson
 */
public class MentionFilter extends AbstractCommandFilter {

    /**
     * Creates a MentionFilter with given children.
     *
     * @param children The children filters, which will be checked in order
     *                 if this filter tests to be {@code true}
     */
    public MentionFilter(CommandFilter... children) {
        super(children);
    }

    @Override
    protected boolean preTest(TextMessageEvent event, Command command) {
        return event.getMessage().getChat().getType() == ChatType.PRIVATE || command.isMentioned();
    }

}
