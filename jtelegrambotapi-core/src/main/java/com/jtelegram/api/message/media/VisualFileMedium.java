package com.jtelegram.api.message.media;

import lombok.Getter;
import lombok.ToString;

/**
 * Similar to File medium, this type itself does
 * not exist in the Telegram API however it holds
 * the shared width / height fields of videos and
 * photos.
 */
@Getter
@ToString(callSuper = true)
public abstract class VisualFileMedium extends FileMedium {
    private int width;
    private int height;
}
