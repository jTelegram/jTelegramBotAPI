package com.jtelegram.api.commands.filters;

import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;

/**
 * A {@link CommandFilter} testing if the command is a given
 * string, with optional case sensitivity.
 *
 * @author Nick Robson
 */
public class TextFilter extends CommandFilter {

    private final String command;
    private final boolean caseSensitive;

    /**
     * Creates a TextFilter with given children.
     *
     * @param command The command name to match
     * @param caseSensitive Whether to enforce case sensitivity or not
     * @param children The children filters, which will be checked in order
     *                 if this filter tests to be {@code true}
     */
    public TextFilter(String command, boolean caseSensitive, CommandFilter... children) {
        super(children);
        this.command = command;
        this.caseSensitive = caseSensitive;
    }

    @Override
    protected boolean preTest(TextMessageEvent event, Command command) {
        return caseSensitive
                ? this.command.equals(command.getBaseCommand())
                : this.command.equalsIgnoreCase(command.getBaseCommand());
    }

}
