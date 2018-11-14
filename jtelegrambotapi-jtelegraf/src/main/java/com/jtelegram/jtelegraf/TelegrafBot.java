package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.events.message.MessageEvent;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.MessageType;
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

    private final Map<Class<? extends Update>, List<TelegrafUpdateListener<?, ?>>> updateListeners = new HashMap<>();
    private final Map<Class<? extends Message>, List<TelegrafMessageListener<?, ?>>> messageListeners = new HashMap<>();

    public TelegrafBot(TelegramBot bot) {
        bot.getEventRegistry().registerEvent(UpdateEvent.class, event -> {
            Update<?> update = event.getUpdate();
            Class<? extends Update> updateClass = update.getClass();
            List<TelegrafUpdateListener<?, ?>> listeners = updateListeners.get(updateClass);
            if (listeners != null) {
                TelegrafUpdateContext<?, ?> context = new TelegrafUpdateContext<>(update);
                for (TelegrafUpdateListener listener : listeners) {
                    try {
                        //noinspection unchecked
                        listener.onUpdate(context, bot);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        bot.getEventRegistry().registerEvent(MessageEvent.class, event -> {
            Update.MessageUpdate update = (Update.MessageUpdate) event.getUpdate();
            Message<?> message = update.getContents();
            Class<? extends Message> messageClass = message.getClass();
            List<TelegrafMessageListener<?, ?>> listeners = messageListeners.get(messageClass);
            if (listeners != null) {
                //noinspection unchecked
                TelegrafMessageContext<?, ?> context = new TelegrafMessageContext<>(update, message);
                for (TelegrafMessageListener listener : listeners) {
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
    void on(UpdateType<T> updateType, TelegrafUpdateListener<T, U> listener) {
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

    public <C, T extends Message<C>, E extends MessageEvent<T>>
    void on(MessageType<C, T, E> messageType, TelegrafMessageListener<C, T> listener) {
        Class<? extends Message> messageClass = messageType.getMessageClass();

        //noinspection Duplicates
        messageListeners.compute(
                messageClass,
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
