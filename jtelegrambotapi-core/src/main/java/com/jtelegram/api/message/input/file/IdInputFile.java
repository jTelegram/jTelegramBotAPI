package com.jtelegram.api.message.input.file;

import com.jtelegram.api.message.media.FileMedium;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class IdInputFile implements InputFile<String> {
    // this is the id
    private String data;

    public static IdInputFile of(String data) {
        return new IdInputFile(data);
    }

    public static IdInputFile of(FileMedium medium) {
        return new IdInputFile(medium.getFileId());
    }
}
