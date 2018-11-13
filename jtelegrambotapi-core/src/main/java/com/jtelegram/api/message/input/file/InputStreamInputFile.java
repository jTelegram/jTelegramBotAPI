package com.jtelegram.api.message.input.file;

import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;

@Getter
@ToString
@AllArgsConstructor
public class InputStreamInputFile implements InputFile<InputStream> {
    private final String identifier;
    private final InputStream data;

    @Override
    public boolean isAttachable() {
        return false;
    }

    @Override
    public void attachTo(MultipartBody.Builder builder) {
        builder.addFormDataPart(identifier, identifier, new RequestBody() {
            @Override
            public MediaType contentType() {
                return InputFileRequest.OCTET_STREAM_TYPE;
            }

            @Override
            public void writeTo(@Nonnull BufferedSink bufferedSink) throws IOException {
                if (data.markSupported()) {
                    try {
                        data.reset();
                    } catch (IOException ignored) {
                        // mark may not have been called
                    }
                }
                bufferedSink.writeAll(Okio.source(data));
            }
        });
    }
}
