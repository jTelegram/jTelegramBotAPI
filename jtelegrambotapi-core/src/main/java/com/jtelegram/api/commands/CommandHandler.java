package com.jtelegram.api.commands;

import com.jtelegram.api.commands.filters.CommandFilter;
import com.jtelegram.api.events.message.TextMessageEvent;

public abstract class CommandHandler extends CommandFilter {

    public abstract void onCommand(TextMessageEvent event, Command command);

    @Override
    protected final boolean _test(TextMessageEvent event, Command command) {
        try {
            onCommand(event, command);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

}
