package io.jtelegram.api.user;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private long id;
    @SerializedName("is_bot")
    private boolean bot;
    private String firstName;
    private String lastName;
    private String username;
    private String languageCode;

}
