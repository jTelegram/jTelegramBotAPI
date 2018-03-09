package com.jtelegram.api.menu;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MenuRow {
    private List<? extends MenuButton> buttons;

    public static MenuRow from(MenuButton... buttons) {
        return new MenuRow(Arrays.asList(buttons));
    }
}
