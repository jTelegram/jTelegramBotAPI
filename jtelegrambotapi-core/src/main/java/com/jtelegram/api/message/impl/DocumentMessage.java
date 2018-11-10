package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.CaptionableMessage;
import com.jtelegram.api.message.media.Document;
import com.jtelegram.api.requests.message.edit.EditMessageCaption;
import com.jtelegram.api.requests.message.edit.EditMessageMedia;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class DocumentMessage extends CaptionableMessage<Document> {
    private Document document;

    @Override
    public Document getContent() {
        return document;
    }

    /**
     * Creates a request builder for editing the caption of this message.
     *
     * @return the request builder
     */
    public EditMessageCaption.EditMessageCaptionBuilder toEditCaptionRequest() {
        return EditMessageCaption.forMessage(this);
    }

    /**
     * Creates a request builder for editing the media shown in this message.
     *
     * @return the request builder
     */
    public EditMessageMedia.EditMessageMediaBuilder toEditMediaRequest() {
        return EditMessageMedia.forMessage(this);
    }
}
