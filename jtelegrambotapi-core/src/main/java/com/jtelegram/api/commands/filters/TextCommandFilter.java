package com.jtelegram.api.commands.filters;

import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;

/**
 * @author Nick Robson
 */
public class TextCommandFilter extends CommandFilter {

    private final String command;
    private final boolean ignoreCase;

    public TextCommandFilter(String command, boolean ignoreCase, CommandFilter... children) {
        super(children);
        this.command = command;
        this.ignoreCase = ignoreCase;
    }

    @Override
    protected boolean _test(TextMessageEvent event, Command command) {
        return ignoreCase
                ? this.command.equalsIgnoreCase(command.getBaseCommand())
                : this.command.equals(command.getBaseCommand());
    }

}
