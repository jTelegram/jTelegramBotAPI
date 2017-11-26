package com.jtelegram.api.message.input.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URL;

@AllArgsConstructor
@Getter
public class ExternalInputFile implements InputFile<URL> {
    private URL data;
}
