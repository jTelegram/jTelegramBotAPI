package com.jtelegram.api.inline.input;

import com.jtelegram.api.requests.message.framework.ParseMode;
import com.jtelegram.api.util.TextBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class InputTextMessageContent extends InputMessageContent {
    private String messageText;
    private ParseMode parseMode;
    private Boolean disableWebPagePreview;

    public static class InputTextMessageContentBuilder {
        public InputTextMessageContentBuilder messageText(TextBuilder builder) {
            this.parseMode = ParseMode.HTML;
            this.messageText = builder.toHtml();
            return this;
        }

        public InputTextMessageContentBuilder messageText(String str) {
            this.messageText = str;
            return this;
        }
    }
}
