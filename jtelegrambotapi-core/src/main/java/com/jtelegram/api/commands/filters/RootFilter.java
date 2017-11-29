package com.jtelegram.api.commands.filters;

import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;

/**
 * A {@link CommandFilter} that merely groups filters.
 *
 * @author Nick Robson
 */
public class RootFilter extends AbstractCommandFilter {

    private static CommandFilter[] combine(CommandFilter first, CommandFilter... remaining) {
        CommandFilter[] filters = new CommandFilter[remaining.length + 1];
        filters[0] = first;
        System.arraycopy(remaining, 0, filters, 1, remaining.length);
        return filters;
    }

    /**
     * Creates a RootFilter with given children.
     *
     * @param filter The first filter
     * @param children The remaining children filters, which will be checked in order
     *                 if this filter tests to be {@code true}
     */
    public RootFilter(CommandFilter filter, CommandFilter... children) {
        super(combine(filter, children));
    }

    @Override
    protected boolean preTest(TextMessageEvent event, Command command) {
        return true;
    }
}
