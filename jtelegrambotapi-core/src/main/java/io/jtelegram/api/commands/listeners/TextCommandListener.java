package io.jtelegram.api.commands.listeners;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TextCommandListener implements CommandListener {
    private final String text;
    private final boolean caseSensitive;

    /**
     * Creates a TextCommandListener with case sensitivity defaulted to off.
     *
     * @param text
     */
    public TextCommandListener(String text) {
        this.text = text;
        this.caseSensitive = false;
    }

    @Override
    public boolean trigger(String s) {
        if (s == null) return false;

        if (caseSensitive)
            return text.equalsIgnoreCase(s);

        return text.equals(s);
    }
}
