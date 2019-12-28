package com.jtelegram.api.message.input.file;

import com.jtelegram.api.util.MultipartBodyPublisher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.http.HttpRequest;

import lombok.ToString;

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
    public void attachTo(MultipartBodyPublisher.Builder builder) {
        HttpRequest.BodyPublisher filePublisher;
        try {
            filePublisher = HttpRequest.BodyPublishers.ofFile(data.toPath());
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Local file not found", ex);
        }
        String identifier = getIdentifier();
        builder.addPart(
                MultipartBodyPublisher.Part.forBodyPublisher(identifier, identifier, InputFileRequest.OCTET_STREAM_TYPE, filePublisher));
    }
}
