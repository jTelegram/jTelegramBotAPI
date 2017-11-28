package com.jtelegram.api.test;

import com.jtelegram.api.commands.Command;

public interface TestModule {
    /**
     * Validate if the test can be performed in these conditions (args and type of chat)
     * @return null if the test can be performed in these conditions, otherwise error message
     */
    String validate(Command command);

    void handle(String[] args, Command command) throws Exception;

    String getName();
}
