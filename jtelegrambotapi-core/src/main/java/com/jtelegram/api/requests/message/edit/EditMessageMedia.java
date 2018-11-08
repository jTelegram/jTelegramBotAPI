package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.impl.AudioMessage;
import com.jtelegram.api.message.impl.DocumentMessage;
import com.jtelegram.api.message.impl.PhotoMessage;
import com.jtelegram.api.message.impl.VideoMessage;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.media.InputMedia;
import com.jtelegram.api.requests.message.framework.req.SendableInputFileInlineRequest;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EditMessageMedia extends SendableInputFileInlineRequest<Message> {
    private InputMedia media;

    @Builder
    protected EditMessageMedia(Consumer<Message> callback, Consumer<TelegramException> errorHandler,
                               ChatId chatId, Integer messageId, String inlineMessageId, InputMedia media) {
        super("editMessageMedia", Message.class, callback, errorHandler, chatId, messageId, inlineMessageId);
        this.media = media;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && media != null;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return media.getAllMedia();
    }

    public static EditMessageMediaBuilder forMessage(AudioMessage message) {
        Objects.requireNonNull(message, "audio message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

    public static EditMessageMediaBuilder forMessage(DocumentMessage message) {
        Objects.requireNonNull(message, "document message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

    public static EditMessageMediaBuilder forMessage(PhotoMessage message) {
        Objects.requireNonNull(message, "photo message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

    public static EditMessageMediaBuilder forMessage(VideoMessage message) {
        Objects.requireNonNull(message, "video message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

    public static EditMessageMediaBuilder forInlineMessage(String inlineMessageId) {
        Objects.requireNonNull(inlineMessageId, "inline message ID cannot be null");
        return builder().inlineMessageId(inlineMessageId);
    }
}
