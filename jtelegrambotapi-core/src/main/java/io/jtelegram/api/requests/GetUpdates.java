package io.jtelegram.api.requests;


import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;
import io.jtelegram.api.update.Update;
import io.jtelegram.api.update.UpdateType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.function.Consumer;

/**
 * Method for polling and retrieve latest updates
 */
@Getter
@ToString
public class GetUpdates extends QueryTelegramRequest<Update[]> {
    private final int offset;
    private final int limit;
    private final int timeout;
    private final List<UpdateType> allowedUpdates;

    @Builder
    public GetUpdates(Consumer<Update[]> callback, Consumer<TelegramException> errorHandler, int offset, int limit, int timeout, List<UpdateType> allowedUpdates) {
        super("getUpdates", Update[].class, callback, errorHandler);
        this.offset = offset;
        this.limit = limit;
        this.timeout = timeout;
        this.allowedUpdates = allowedUpdates;
    }

    @Override
    protected boolean isValid() {
        return limit >= 0 && timeout >= 0;
    }
}
