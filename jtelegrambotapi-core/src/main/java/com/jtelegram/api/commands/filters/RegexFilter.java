package com.jtelegram.api.commands.filters;

import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;
import java.util.regex.Pattern;

/**
 * A {@link CommandFilter} testing if the command matches
 * a certain {@link Pattern regular expression pattern}.
 *
 * @author Nick Robson
 */
public class RegexFilter extends AbstractCommandFilter {

    private final Pattern pattern;

    /**
     * Creates a RegexFilter with given children.
     *
     * @param pattern The pattern to match against
     * @param children The children filters, which will be checked in order
     *                 if this filter tests to be {@code true}
     */
    public RegexFilter(Pattern pattern, CommandFilter... children) {
        super(children);
        this.pattern = pattern;
    }

    @Override
    protected boolean preTest(TextMessageEvent event, Command command) {
        return pattern.matcher(command.getBaseCommand()).matches();
    }

}
