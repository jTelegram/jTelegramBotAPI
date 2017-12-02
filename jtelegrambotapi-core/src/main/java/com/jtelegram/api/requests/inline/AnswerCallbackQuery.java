package com.jtelegram.api.requests.inline;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class AnswerCallbackQuery extends UpdateTelegramRequest {
    @SerializedName("callback_query_id")
    private String queryId;
    private String text;
    private Boolean showAlert;
    private String url;
    private Integer cacheTime;

    @Builder
    private AnswerCallbackQuery(Consumer<TelegramException> errorHandler, Runnable callback, String queryId,
                                String text, Boolean showAlert, String url, Integer cacheTime) {
        super("answerCallbackQuery", errorHandler, callback);
        this.queryId = queryId;
        this.text = text;
        this.showAlert = showAlert;
        this.url = url;
        this.cacheTime = cacheTime;
    }

    @Override
    protected boolean isValid() {
        return queryId != null;
    }
}
