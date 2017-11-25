package io.jtelegram.api.requests.webhooks;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.input.file.InputFile;
import io.jtelegram.api.message.input.file.InputFileRequest;
import io.jtelegram.api.message.input.file.LocalInputFile;
import io.jtelegram.api.requests.framework.UpdateTelegramRequest;
import io.jtelegram.api.requests.message.framework.req.InputFileMessageUpdate;
import io.jtelegram.api.update.UpdateType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString
public class SetWebhook extends UpdateTelegramRequest {
    public static final transient List<Integer> PERMITTED_PORTS = Arrays.asList(443, 80, 88, 8443);
    private URL url;
    private LocalInputFile certificate;
    private Integer maxConnections;
    private List<UpdateType> allowedTypes;

    @Builder
    private SetWebhook(Consumer<TelegramException> errorHandler, Runnable callback, URL url, LocalInputFile certificate, Integer maxConnections, List<UpdateType> allowedTypes) {
        super("setWebhook", errorHandler, callback);
        this.url = url;
        this.certificate = certificate;
        this.maxConnections = maxConnections;
        this.allowedTypes = allowedTypes;
    }

    @Override
    public Request.Builder build(TelegramBot bot) {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        bodyBuilder.addFormDataPart("url", url.toString());

        if (certificate != null) {
            bodyBuilder.addFormDataPart("certificate", certificate.getData().getName(), RequestBody.create(InputFileRequest.OCTET_STREAM_TYPE, certificate.getData()));
        }

        if (maxConnections != null) {
            bodyBuilder.addFormDataPart("max_connections", String.valueOf(maxConnections));
        }

        if (allowedTypes != null) {
            bodyBuilder.addFormDataPart("allowedTypes", gson.toJson(allowedTypes));
        }

        return super.build(bot).post(bodyBuilder.build());
    }

    @Override
    protected boolean isValid() {
        return url != null && PERMITTED_PORTS.contains(url.getPort()) && (maxConnections == null || maxConnections > 0);
    }
}
