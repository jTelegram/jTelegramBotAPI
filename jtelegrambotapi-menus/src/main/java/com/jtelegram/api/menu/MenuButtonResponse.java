package com.jtelegram.api.menu;

import java.net.URL;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.Builder;

/**
 * A visual response to a user clicking on a button.
 */
@Builder
public class MenuButtonResponse {

    @Nonnull
    private final String text;
    private final boolean showAlert;
    private final URL url;
    private final Integer cacheTime;

    /**
     * Gets the text displayed to the user.
     *
     * @return The text
     */
    @Nonnull
    public String getText() {
        return text;
    }

    /**
     * Whether or not to show a confirmation dialog or not.
     *
     * @return True if and only if such a dialog is to be shown
     */
    public boolean isShowAlert() {
        return showAlert;
    }

    /**
     * Gets the URL to go to when clicked.
     *
     * @return The URL
     */
    @Nullable
    public URL getURL() {
        return url;
    }

    /**
     * Gets how long this response should be cached for.
     *
     * @return The cache time
     */
    @Nullable
    public Integer getCacheTime() {
        return cacheTime;
    }

}
