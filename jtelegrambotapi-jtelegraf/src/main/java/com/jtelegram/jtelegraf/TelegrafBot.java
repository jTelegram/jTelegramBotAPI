package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateContents;
import com.jtelegram.api.update.UpdateType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author Nick Robson
 */
@ParametersAreNonnullByDefault
public class TelegrafBot {

    private final Map<Class<? extends Update>, List<TelegrafListener<?, ?>>> updateListeners = new HashMap<>();

    public TelegrafBot(TelegramBot bot) {
        bot.getEventRegistry().registerEvent(UpdateEvent.class, event -> {
            Update<?> update = event.getUpdate();
            Class<? extends Update> updateClass = update.getClass();
            List<TelegrafListener<?, ?>> listeners = updateListeners.get(updateClass);
            if (listeners != null) {
                TelegrafContext<?, ?> context = new TelegrafContext<>(update);
                for (TelegrafListener listener : listeners) {
                    try {
                        //noinspection unchecked
                        listener.onUpdate(context, bot);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public <T extends Update<U>, U extends UpdateContents>
    void on(UpdateType<T> updateType, TelegrafListener<T, U> listener) {
        Class<? extends Update> updateClass = updateType.getUpdateClass();

        //noinspection Duplicates
        updateListeners.compute(
                updateClass,
                (k, v) -> {
                    if (v == null) {
                        v = new ArrayList<>();
                    }
                    v.add(listener);
                    return v;
                }
        );
    }

}
