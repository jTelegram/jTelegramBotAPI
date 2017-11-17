package io.jtelegram.api.chat;

import io.jtelegram.api.chat.type.LargeChat;
import lombok.Getter;

@Getter
public class SupergroupChat extends LargeChat {
    private String stickerSetName;
    private boolean canSetStickerSet;
}
