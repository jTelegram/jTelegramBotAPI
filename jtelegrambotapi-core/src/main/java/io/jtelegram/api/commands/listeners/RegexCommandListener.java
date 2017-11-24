package io.jtelegram.api.commands.listeners;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
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
