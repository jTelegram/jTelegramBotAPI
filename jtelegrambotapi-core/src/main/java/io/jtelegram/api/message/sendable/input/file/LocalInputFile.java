package io.jtelegram.api.message.sendable.input.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@AllArgsConstructor
@Getter
public class LocalInputFile implements InputFile<File> {
    private File data;
}
