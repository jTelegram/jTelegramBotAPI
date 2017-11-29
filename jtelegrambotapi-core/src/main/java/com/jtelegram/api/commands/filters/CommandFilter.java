package com.jtelegram.api.commands.filters;

import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;

/**
 * Filters messages out according for if they match certain
 * test criteria, as defined by the implementing sub-class.
 */
public interface CommandFilter {

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
    boolean test(TextMessageEvent event, Command command);

}
