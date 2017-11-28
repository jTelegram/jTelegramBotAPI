package com.jtelegram.api.commands.filters;

import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;

/**
 * @author Nick Robson
 */
public abstract class CommandFilter {

    private final CommandFilter[] children;

    public CommandFilter(CommandFilter... children) {
        this.children = children;
    }

    protected abstract boolean _test(TextMessageEvent event, Command command);

    public final boolean test(TextMessageEvent event, Command command) {
        try {
            if (_test(event, command)) {
                for (CommandFilter child : children) {
                    if (child != null && child.test(event, command)) {
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
