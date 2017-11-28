package com.jtelegram.api.commands.filters;

import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;
import java.util.regex.Pattern;

/**
 * @author Nick Robson
 */
public class RegexCommandFilter extends CommandFilter {

    private final Pattern pattern;

    public RegexCommandFilter(Pattern pattern, CommandFilter... children) {
        super(children);
        this.pattern = pattern;
    }

    @Override
    protected boolean _test(TextMessageEvent event, Command command) {
        return pattern.matcher(command.getBaseCommand()).matches();
    }

}
