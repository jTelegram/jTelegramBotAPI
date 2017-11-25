package io.jtelegram.api.inline.result.framework;

import io.jtelegram.api.inline.input.InputMessageContent;
import io.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import io.jtelegram.api.message.input.file.IdInputFile;
import lombok.Getter;

@Getter
public abstract class InlineResult {
    private InlineResultType type = InlineResultType.typeFrom(getClass());
    private String id;
    private InlineKeyboardMarkup replyMarkup;
    private InputMessageContent inputMessageContent;

    protected InlineResult(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this.id = id;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    public interface Cached {
        IdInputFile getFileId();
    }

    public interface Captioned {
        String getCaption();
    }

    public interface Describeable {
        String getDescription();
    }

    public interface Duratable {
        Integer getDuration();
    }

    public interface Titled {
        String getTitle();
    }

    public interface Urlable {
        String getUrl();
    }

    public interface Visual {
        Integer getWidth();
        Integer getHeight();
    }
}
