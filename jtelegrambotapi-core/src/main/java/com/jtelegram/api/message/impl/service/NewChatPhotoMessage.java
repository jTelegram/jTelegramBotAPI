package com.jtelegram.api.message.impl.service;

import com.jtelegram.api.message.media.PhotoSize;
import lombok.Getter;

import java.util.List;

@Getter
public class NewChatPhotoMessage extends ServiceMessage {
    private List<PhotoSize> newChatPhoto;
}
