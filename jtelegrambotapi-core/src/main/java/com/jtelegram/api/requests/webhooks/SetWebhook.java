package com.jtelegram.api.requests.webhooks;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.input.file.InputFileRequest;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import com.jtelegram.api.update.UpdateType;
import com.jtelegram.api.util.MultipartBodyPublisher;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.FileNotFoundException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.util.Arrays;
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
    public HttpRequest.Builder build(TelegramBot bot) {
        MultipartBodyPublisher.Builder bodyBuilder = new MultipartBodyPublisher.Builder();

        bodyBuilder.addPart(MultipartBodyPublisher.Part.forFormData("url", url.toString()));

        if (certificate != null) {
            HttpRequest.BodyPublisher certificatePublisher;
            try {
                certificatePublisher = HttpRequest.BodyPublishers.ofFile(certificate.getData().toPath());
            } catch (FileNotFoundException ex) {
                throw new IllegalArgumentException("Certificate not found", ex);
            }
            bodyBuilder.addPart(
                    MultipartBodyPublisher.Part.forBodyPublisher(
                            "certificate",
                            certificate.getData().getName(),
                            InputFileRequest.OCTET_STREAM_TYPE,
                            certificatePublisher));
        }

        if (maxConnections != null) {
            bodyBuilder.addPart(
                    MultipartBodyPublisher.Part.forFormData("max_connections", String.valueOf(maxConnections))
            );
        }

        if (allowedTypes != null) {
            bodyBuilder.addPart(MultipartBodyPublisher.Part.forFormData("allowedTypes", gson.toJson(allowedTypes)));
        }

        return super.build(bot).header("Content-Type", "multipart/form-data").POST(bodyBuilder.build());
    }

    @Override
    protected boolean isValid() {
        return url != null && PERMITTED_PORTS.contains(url.getPort()) && (maxConnections == null || maxConnections > 0);
    }
}
