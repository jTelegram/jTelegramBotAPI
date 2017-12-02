package com.jtelegram.api.user;

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

    /**
     * Gets the user's full name.
     *
     * @return The full name
     */
    public String getFullname() {
        return new StringBuilder(firstName).append(" ").append(lastName).toString();
    }

    /**
     * Tries the get the username of the user.
     * If it doesn't exist, it will fallback on getting the user the full name of the user.
     *
     * @return The username fallback
     */
    public String getUsernameFallbackName() {
        if (username == null) {
            return getFullname();
        } else {
            return username;
        }
    }

}
