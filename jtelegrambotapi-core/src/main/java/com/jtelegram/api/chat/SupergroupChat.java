package com.jtelegram.api.chat;

import com.jtelegram.api.chat.type.LargeChat;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SupergroupChat extends LargeChat {
    private String stickerSetName;
    private boolean canSetStickerSet;
}
