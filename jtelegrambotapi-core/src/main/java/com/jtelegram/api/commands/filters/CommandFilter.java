package com.jtelegram.api.commands.filters;

import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;

/**
 * Filters messages out according for if they match certain
 * test criteria, as defined by the implementing sub-class.
 *
 * @author Nick Robson
 */
public abstract class CommandFilter {

    private final CommandFilter[] children;

    /**
     * Creates a CommandFilter with given children.<br>
     * <i>All subclasses of CommandFilter are expected to support
     * multiple children filters (except CommandHandler, which is
     * a special case).</i>
     *
     * @param children The children filters, which will be checked in order
     *                 if this filter tests to be {@code true}
     */
    public CommandFilter(CommandFilter... children) {
        this.children = children;
    }

    protected abstract boolean preTest(TextMessageEvent event, Command command);

    /**
     * Checks to see if the given event and command test to be {@code true}
     * according to this filter.
     *
     * @param event The event to be tested
     * @param command The command data executed in this event
     *
     * @return True iff this filter allows this event and command combination to
     * go ahead.
     */
    public final boolean test(TextMessageEvent event, Command command) {
        try {
            if (preTest(event, command)) {
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
