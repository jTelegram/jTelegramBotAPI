package com.jtelegram.api.test.misc;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.message.input.file.IdInputFile;
import com.jtelegram.api.message.input.media.InputMedia;
import com.jtelegram.api.message.input.media.PhotoInputMedia;
import com.jtelegram.api.requests.GetUserProfilePhotos;
import com.jtelegram.api.requests.message.send.SendMediaGroup;
import com.jtelegram.api.test.AbstractTestModule;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserProfilePhotosTest extends AbstractTestModule {
    public UserProfilePhotosTest(TelegramBot bot) {
        super(bot);
    }

    @Override
    public String validate(Command command) {
        return null;
    }

    @Override
    public void handle(String[] args, Command command) throws Exception {
        bot.perform(GetUserProfilePhotos.builder()
                .userId(command.getSender().getId())
                .callback((photos) -> {
                    List<InputMedia> media = photos.getPhotos().stream()
                            .map((e) -> e.stream().sorted(
                                    Comparator.comparingInt((s) -> s.getHeight() + s.getWidth())
                            ).findFirst().orElse(null))
                            .map((photo) -> PhotoInputMedia.builder()
                                    .media(IdInputFile.of(photo))
                                    .caption("One of your profile photos")
                                    .build())
                            .collect(Collectors.toList());

                    if (media.size() > 10) {
                        media = media.subList(0, 11);
                    }

                    bot.perform(SendMediaGroup.builder()
                            .chatId(ChatId.of(command.getChat()))
                            .media(media)
                            .errorHandler((ex) -> System.out.println("Could not send profile photos due to " + ex.getDescription()))
                            .build());
                })
                .errorHandler((ex) -> System.out.println("Getting profile photos failed due to " + ex.getDescription()))
                .build());
    }

    @Override
    public String getName() {
        return "user-profile-photos";
    }
}
