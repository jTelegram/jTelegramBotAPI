package com.jtelegram.api.message.input.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import lombok.ToString;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Getter
@ToString
@AllArgsConstructor
public class LocalInputFile implements InputFile<File> {
    private File data;

    @Override
    public String getIdentifier() {
        return data.getName();
    }

    @Override
    public void attachTo(MultipartBody.Builder builder) {
        String identifier = getIdentifier();
        builder.addFormDataPart(identifier, identifier, RequestBody.create(InputFileRequest.OCTET_STREAM_TYPE, data));
    }
}
