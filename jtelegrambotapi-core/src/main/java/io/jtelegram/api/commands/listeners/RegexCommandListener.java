package io.jtelegram.api.commands.listeners;

import io.jtelegram.api.commands.CommandListener;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class RegexCommandListener implements CommandListener {
    private final Pattern pattern;

    @Override
    public boolean trigger(String s) {
        return false;
    }
}
