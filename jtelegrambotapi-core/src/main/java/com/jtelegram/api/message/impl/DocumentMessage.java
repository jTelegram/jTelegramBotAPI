package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.CaptionableMessage;
import com.jtelegram.api.message.media.Document;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DocumentMessage extends CaptionableMessage<Document> {
    private Document document;

    @Override
    public Document getContent() {
        return document;
    }
}
