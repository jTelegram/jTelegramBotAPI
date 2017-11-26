package com.jtelegram.api.user;

import com.jtelegram.api.message.media.PhotoSize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfilePhotos {
    private int totalCount;
    /**
     * List of users profile pictures
     * each list containing up to 4 different
     * sizes
     */
    private List<List<PhotoSize>> photos;
}
