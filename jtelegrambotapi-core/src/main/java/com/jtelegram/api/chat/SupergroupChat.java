package com.jtelegram.api.chat;

import com.jtelegram.api.chat.type.LargeChat;
import lombok.Getter;

@Getter
public class SupergroupChat extends LargeChat {
    private String stickerSetName;
    private boolean canSetStickerSet;
}
