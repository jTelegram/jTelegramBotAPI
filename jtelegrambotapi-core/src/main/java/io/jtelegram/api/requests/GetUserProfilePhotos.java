package io.jtelegram.api.requests;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;
import io.jtelegram.api.user.UserProfilePhotos;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class GetUserProfilePhotos extends QueryTelegramRequest<UserProfilePhotos> {
    private Integer userId;
    private Integer offset;
    private Integer limit;

    @Builder
    protected GetUserProfilePhotos(Consumer<UserProfilePhotos> callback, Consumer<TelegramException> errorHandler, Integer userId, Integer offset, Integer limit) {
        super("getUserProfilePhotos", UserProfilePhotos.class, callback, errorHandler);
        this.userId = userId;
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    protected boolean isValid() {
        return userId != null && (offset == null || offset >= 0) &&
                (limit == null || limit >= 1 && limit <= 100);
    }
}
