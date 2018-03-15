package com.jtelegram.api.message.input.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class LocalInputFile implements InputFile<File> {
    private File data;
}
