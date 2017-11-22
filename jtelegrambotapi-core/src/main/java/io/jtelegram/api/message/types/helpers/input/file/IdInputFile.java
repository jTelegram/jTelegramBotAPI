package io.jtelegram.api.message.types.helpers.input.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IdInputFile implements InputFile<String> {
    // this is the id
    private String data;
}
