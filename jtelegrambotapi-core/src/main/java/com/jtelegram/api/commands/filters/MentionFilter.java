package com.jtelegram.api.commands.filters;

import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;
import com.jtelegram.api.message.entity.MessageEntityType;

/**
 * @author Nick Robson
 */
public class MentionFilter extends CommandFilter {

    public MentionFilter(CommandFilter... children) {
        super(children);
    }

    @Override
    protected boolean _test(TextMessageEvent event, Command command) {
        String botUsername = event.getBot().getBotInfo().getUsername();

        return event.getMessage().getEntities().stream()
                .filter(me -> me.getType() == MessageEntityType.MENTION)
                .filter(me -> botUsername.equalsIgnoreCase(me.toString()))
                .count() > 0;
    }

}
