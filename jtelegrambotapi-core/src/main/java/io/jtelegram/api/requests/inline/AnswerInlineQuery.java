package io.jtelegram.api.requests.inline;

import com.google.gson.annotations.SerializedName;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.inline.result.framework.InlineResult;
import io.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString
public class AnswerInlineQuery extends UpdateTelegramRequest {
    @SerializedName("inline_query_id")
    private String queryId;
    private List<InlineResult> results;
    private Integer cacheTime;
    private Boolean isPersonal;
    private String nextOffset;
    private String switchPmText;
    private String switchPmParameter;

    @Builder
    private AnswerInlineQuery(String endPoint, Consumer<TelegramException> errorHandler,
                             Runnable callback, String queryId, List<InlineResult> results,
                             Integer cacheTime, Boolean isPersonal, String nextOffset,
                             String switchPmText, String switchPmParameter) {
        super(endPoint, errorHandler, callback);
        this.queryId = queryId;
        this.results = results;
        this.cacheTime = cacheTime;
        this.isPersonal = isPersonal;
        this.nextOffset = nextOffset;
        this.switchPmText = switchPmText;
        this.switchPmParameter = switchPmParameter;
    }

    @Override
    protected boolean isValid() {
        return queryId != null && results != null && (cacheTime == null || cacheTime > 0) &&
                (nextOffset == null || nextOffset.getBytes().length < 64);
    }

    public static class AnswerInlineQueryBuilder {
        public AnswerInlineQueryBuilder addResult(InlineResult result) {
            if (results == null) {
                results = new ArrayList<>();
            }

            results.add(result);
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
}
