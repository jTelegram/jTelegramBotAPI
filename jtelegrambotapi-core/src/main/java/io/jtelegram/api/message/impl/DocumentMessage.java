package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.CaptionableMessage;
import io.jtelegram.api.message.media.Document;
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
