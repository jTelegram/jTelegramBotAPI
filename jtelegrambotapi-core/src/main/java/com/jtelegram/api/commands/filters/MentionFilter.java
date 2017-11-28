package com.jtelegram.api.commands.filters;

import com.jtelegram.api.chat.ChatType;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;
import com.jtelegram.api.message.entity.MessageEntityType;

/**
 * A {@link CommandFilter} testing if the bot was mentioned in
 * the command.
 *
 * @author Nick Robson
 */
public class MentionFilter extends CommandFilter {

    /**
     * Creates a CommandFilter with given children.
     *
     * @param children The children filters, which will be checked in order
     *                 if this filter tests to be {@code true}
     */
    public MentionFilter(CommandFilter... children) {
        super(children);
    }

    @Override
    protected boolean preTest(TextMessageEvent event, Command command) {
        String botUsername = event.getBot().getBotInfo().getUsername();

        return event.getMessage().getChat().getType() == ChatType.PRIVATE
                || event.getMessage().getEntities().stream()
                        .filter(me -> me.getType() == MessageEntityType.MENTION)
                        .filter(me -> botUsername.equalsIgnoreCase(me.toString()))
                        .count() > 0;
    }

}
