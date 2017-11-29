package com.jtelegram.api.commands;

import com.jtelegram.api.commands.filters.CommandFilter;
import com.jtelegram.api.events.message.TextMessageEvent;

public interface CommandHandler extends CommandFilter {

    /**
     * Runs this command.
     *
     * @param event The event containing the command
     * @param command The command being run
     */
    void onCommand(TextMessageEvent event, Command command);

    @Override
    default boolean test(TextMessageEvent event, Command command) {
        try {
            onCommand(event, command);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

}
