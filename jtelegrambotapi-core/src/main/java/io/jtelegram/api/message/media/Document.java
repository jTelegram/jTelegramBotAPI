package io.jtelegram.api.message.media;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Document extends FileMedium implements MimeableMedium, ThumbableMedium {
    @SerializedName("thumb")
    private PhotoSize thumbnail;
    private String fileName;
    private String mimeType;
}
