package com.jtelegram.api.commands.filters;

import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;

/**
 * An abstract implementation of {@link CommandFilter}
 */
public abstract class AbstractCommandFilter implements CommandFilter {

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
    public AbstractCommandFilter(CommandFilter... children) {
        this.children = children;
    }

    protected abstract boolean preTest(TextMessageEvent event, Command command);

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
