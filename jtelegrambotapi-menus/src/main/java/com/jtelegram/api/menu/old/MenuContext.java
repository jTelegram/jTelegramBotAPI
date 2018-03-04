package com.jtelegram.api.menu.old;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A simple key-value store across screens of a menu.
 * Not persistent over restarts of the bot.
 */
public class MenuContext {

    private final Map<String, Object> mapping = new HashMap<>();

    /**
     * Gets if a key is in the context mapping.
     *
     * @param key The key
     *
     * @return True if and only if the key is in the mapping.
     */
    public boolean hasKey(@Nonnull String key) {
        return mapping.containsKey(key);
    }

    /**
     * Gets the value associated with a key, or null if none.
     *
     * @param key The key
     * @param <T> The type of the value
     *
     * @return The value, or null if none
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T get(@Nonnull String key) {
        return (T) mapping.get(key);
    }

    /**
     * Sets the value associated with a key, and returns the old mapping.
     *
     * @param key The key
     * @param val The new value
     * @param <T> The type of the value
     *
     * @return The old value, or null if none.
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T set(@Nonnull String key, @Nonnull T val) {
        return (T) mapping.put(key, val);
    }

    /**
     * Removes the value associated with the given key.
     *
     * @param key The key
     * @param <T> The type of the value
     *
     * @return The old value, or null if none.
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T remove(@Nonnull String key) {
        return (T) mapping.remove(key);
    }

}
