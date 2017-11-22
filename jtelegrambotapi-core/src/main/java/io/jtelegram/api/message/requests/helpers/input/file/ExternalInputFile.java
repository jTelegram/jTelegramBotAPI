package io.jtelegram.api.message.requests.helpers.input.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URL;

@AllArgsConstructor
@Getter
public class ExternalInputFile implements InputFile<URL> {
    private URL data;
}
