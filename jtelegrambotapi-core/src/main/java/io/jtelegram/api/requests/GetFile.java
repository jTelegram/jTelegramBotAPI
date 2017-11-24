package io.jtelegram.api.requests;

import io.jtelegram.api.TelegramFile;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class GetFile extends QueryTelegramRequest<TelegramFile> {
    private String fileId;

    protected GetFile(Consumer<TelegramFile> callback, Consumer<TelegramException> errorHandler, String fileId) {
        super("getFile", TelegramFile.class, callback, errorHandler);
        this.fileId = fileId;
    }

    @Override
    protected boolean isValid() {
        return fileId != null;
    }
}
