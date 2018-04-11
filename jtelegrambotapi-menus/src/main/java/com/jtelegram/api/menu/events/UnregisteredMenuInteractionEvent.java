package com.jtelegram.api.menu.events;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = {"uuid", "rowIndex", "columnIndex"})
@EqualsAndHashCode(of = {"uuid", "rowIndex", "columnIndex"}, callSuper = false)
public class UnregisteredMenuInteractionEvent extends Event {

    private final CallbackQueryEvent event;
    private final UUID uuid;
    private final int rowIndex;
    private final int columnIndex;

    public UnregisteredMenuInteractionEvent(TelegramBot bot, CallbackQueryEvent event, UUID uuid, int rowIndex, int columnIndex) {
        super(bot);
        this.event = event;
        this.uuid = uuid;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

}
