package com.jtelegram.api.requests;

import com.jtelegram.api.requests.framework.QueryTelegramRequest;
import com.jtelegram.api.TelegramFile;
import com.jtelegram.api.ex.TelegramException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class GetFile extends QueryTelegramRequest<TelegramFile> {
    private String fileId;

    @Builder
    protected GetFile(Consumer<TelegramFile> callback, Consumer<TelegramException> errorHandler, String fileId) {
        super("getFile", TelegramFile.class, callback, errorHandler);
        this.fileId = fileId;
    }

    @Override
    protected boolean isValid() {
        return fileId != null;
    }
}
