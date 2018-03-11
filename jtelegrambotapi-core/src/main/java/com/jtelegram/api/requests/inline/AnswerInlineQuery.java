package com.jtelegram.api.requests.inline;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.events.EventHandler;
import com.jtelegram.api.events.inline.ChosenInlineResultEvent;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.inline.result.ChosenInlineResult;
import com.jtelegram.api.inline.result.InlineResultPhoto;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Getter
@ToString
public class AnswerInlineQuery extends UpdateTelegramRequest {
    public static final ResultSelectionListener RESULT_LISTENER = new ResultSelectionListener();
    @SerializedName("inline_query_id")
    private String queryId;
    private List<InlineResult> results;
    private Integer cacheTime;
    private Boolean isPersonal;
    private String nextOffset;
    private String switchPmText;
    private String switchPmParameter;

    @Builder
    private AnswerInlineQuery(Consumer<TelegramException> errorHandler,
                             Runnable callback, String queryId, List<InlineResult> results,
                             Integer cacheTime, Boolean isPersonal, String nextOffset,
                             String switchPmText, String switchPmParameter, Consumer<ChosenInlineResult> resultHandler) {
        super("answerInlineQuery", errorHandler, callback);
        this.queryId = queryId;
        this.results = results;
        this.cacheTime = cacheTime;
        this.isPersonal = isPersonal;
        this.nextOffset = nextOffset;
        this.switchPmText = switchPmText;
        this.switchPmParameter = switchPmParameter;

        if (resultHandler != null) {
            // if this request executes successfully, put their resultHandler
            // in our listener's callback map.
            super.callback = () -> {
                RESULT_LISTENER.resultCallbacks.put(queryId, resultHandler);
                callback.run();
            };
        }
    }

    @Override
    protected boolean isValid() {
        return queryId != null && results != null && (cacheTime == null || cacheTime > 0) &&
                (nextOffset == null || nextOffset.getBytes().length < 64);
    }

    public static class AnswerInlineQueryBuilder {
        public AnswerInlineQueryBuilder results(List<? extends InlineResult> results) {
            this.results = new ArrayList<>();
            this.results.addAll(results);
            return this;
        }

        public AnswerInlineQueryBuilder addResult(InlineResult result) {
            if (results == null) {
                this.results = new ArrayList<>();
            }

            this.results.add(result);
            return this;
        }

        public AnswerInlineQueryBuilder addAllResults(Collection<InlineResult> result) {
            if (results == null) {
                results = new ArrayList<>();
            }

            results.addAll(result);
            return this;
        }
    }

    public static class ResultSelectionListener implements EventHandler<ChosenInlineResultEvent> {
        private Map<String, Consumer<ChosenInlineResult>> resultCallbacks = new ConcurrentHashMap<>();

        @Override
        public void onEvent(ChosenInlineResultEvent event) {
            ChosenInlineResult result = event.getChosenResult();
            String id = result.getQuery();

            if (resultCallbacks.containsKey(id)) {
                resultCallbacks.remove(id).accept(result);
            }
        }
    }
}
