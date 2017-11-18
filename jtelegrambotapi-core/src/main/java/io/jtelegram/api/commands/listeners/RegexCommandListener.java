package io.jtelegram.api.commands.listeners;

import io.jtelegram.api.commands.CommandListener;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class RegexCommandListener implements CommandListener {
    private final Pattern pattern;

    @Override
    public boolean trigger(String s) {
        // TODO not sure if this is safe to do
        if (pattern.matcher(s).matches()) {
            return true;
        }
        return false;
    }
}
