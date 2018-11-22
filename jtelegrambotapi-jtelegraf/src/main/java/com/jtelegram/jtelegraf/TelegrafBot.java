package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.events.message.MessageEvent;
import com.jtelegram.api.events.message.TextMessageEvent;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.MessageType;
import com.jtelegram.api.message.entity.MessageEntity;
import com.jtelegram.api.message.entity.MessageEntityType;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.message.send.SendText;
import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateContents;
import com.jtelegram.api.update.UpdateType;
import com.jtelegram.api.util.TextBuilder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author Nick Robson
 */
@ParametersAreNonnullByDefault
public class TelegrafBot {

    public static final String COMMAND_START = "start";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_SETTINGS = "settings";

    private final Map<Class<? extends Update>, List<TelegrafUpdateListener<?, ?, ?>>> updateListeners = new HashMap<>();
    private final Map<Class<? extends Message>, List<TelegrafMessageListener<?, ?, ?>>> messageListeners = new HashMap<>();
    private final Map<MessageEntityType, List<TelegrafMessageEntityListener<?, ?>>> messageEntityListeners = new HashMap<>();

    private final List<Map.Entry<String, TelegrafMessageListener<?, String, TextMessage>>> hearsTextListeners = new LinkedList<>();
    private final List<Map.Entry<Predicate<String>, TelegrafMessageListener<?, String, TextMessage>>> hearsPredicateListeners = new LinkedList<>();
    private final List<Map.Entry<Pattern, TelegrafHeardMessageListener>> hearsPatternListeners = new LinkedList<>();

    @Nonnull
    private final TelegramBot bot;

    @SuppressWarnings("unchecked")
    public TelegrafBot(@Nonnull TelegramBot bot) {
        this.bot = bot;
        bot.getEventRegistry().registerEvent(UpdateEvent.class, event -> {
            Update<?> update = event.getUpdate();
            Class<? extends Update> updateClass = update.getClass();
            List<TelegrafUpdateListener<?, ?, ?>> listeners = updateListeners.get(updateClass);
            if (listeners != null) {
                TelegrafUpdateContext<?, ?, ?> context = new TelegrafUpdateContext<>(bot, update);
                for (TelegrafUpdateListener listener : listeners) {
                    try {
                        //noinspection unchecked
                        listener.onUpdate(context);
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
            List<TelegrafMessageListener<?, ?, ?>> listeners = messageListeners.get(messageClass);
            if (listeners != null) {
                //noinspection unchecked
                TelegrafMessageContext<?, ?, ?> context = new TelegrafMessageContext<>(bot, update, message);
                for (TelegrafMessageListener listener : listeners) {
                    try {
                        //noinspection unchecked
                        listener.onMessage(context);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        bot.getEventRegistry().registerEvent(TextMessageEvent.class, event -> {
            Update.MessageUpdate update = event.getUpdate();
            TextMessage message = event.getMessage();

            TelegrafMessageContext context = new TelegrafMessageContext(bot, update, message);
            String text = message.getText();
            hearsTextListeners.forEach(e -> {
                try {
                    if (text.contains(e.getKey())) {
                        e.getValue().onMessage(context);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            hearsPredicateListeners.forEach(e -> {
                try {
                    if (e.getKey().test(text)) {
                        e.getValue().onMessage(context);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            hearsPatternListeners.forEach(e -> {
                try {
                    Matcher matcher = e.getKey().matcher(text);
                    if (matcher.find()) {
                        TelegrafHeardMessageContext ctx = new TelegrafHeardMessageContext(bot, update, message, matcher);
                        e.getValue().onMessage(ctx);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            Map<MessageEntityType, List<MessageEntity>> entities = new HashMap<>();
            event.getMessage().getEntities().forEach(e ->
                    entities.compute(e.getType(), (k, v) -> {
                        if (v == null) {
                            v = new ArrayList<>();
                        }
                        v.add(e);
                        return v;
                    }
            ));
            entities.forEach((type, entity) -> {
                        List<TelegrafMessageEntityListener<?, ?>> listeners = messageEntityListeners.get(type);
                        if (listeners != null) {
                            //noinspection unchecked
                            TelegrafMessageEntityContext entityContext = new TelegrafMessageEntityContext(bot, update, message, type, entity);
                            for (TelegrafMessageEntityListener listener : listeners) {
                                try {
                                    //noinspection unchecked
                                    listener.onMessageEntity(entityContext);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
        });
    }

    /**
     * Registers an update listener, to be called whenever an update of the given type is received.
     *
     * @param updateType the update type to listen for
     * @param listener a listener to be called when an update of the given type is received
     *
     * @param <U> the update content type
     * @param <T> the update type
     */
    public <U extends UpdateContents, T extends Update<U>, UpdateContext extends TelegrafUpdateContext<UpdateContext, U, T>>
    void on(UpdateType<T> updateType, TelegrafUpdateListener<UpdateContext, U, T> listener) {
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

    /**
     * Registers a message listener, to be called whenever a message of the given type is received.
     *
     * @param messageType the message type to listen for
     * @param listener a listener to be called when a message of the given type is received
     *
     * @param <C> the message content type
     * @param <T> the message type, which must contain contents of type {@code <C>}
     * @param <E> the event type, which must contain a message of type {@code <T>}
     */
    public <C, T extends Message<C>, E extends MessageEvent<T>, MessageContext extends TelegrafMessageContext<MessageContext, C, T>>
    void on(MessageType<C, T, E> messageType, TelegrafMessageListener<MessageContext, C, T> listener) {
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

    /**
     * Registers a hear-listener.
     * This listener will be called once for every message that contains the given text. <br/>
     *
     * @param text the text the message must contain
     * @param listener a listener to be called every time a message contains the given text
     */
    @SuppressWarnings("unchecked")
    public void hears(String text, TelegrafMessageListener<? extends TelegrafMessageContext<?, String, TextMessage>, String, TextMessage> listener) {
        hearsTextListeners.add(new AbstractMap.SimpleEntry(text, listener));
    }

    /**
     * Registers a hear-listener.
     * This listener will be called once for every message for which the predicate returns true. <br/>
     *
     * @param predicate the predicate to use to determine if the listener should be called
     * @param listener a listener to be called every time the predicate returns true on a message
     */
    @SuppressWarnings("unchecked")
    public void hears(Predicate<String> predicate, TelegrafMessageListener<? extends TelegrafMessageContext<?, String, TextMessage>, String, TextMessage> listener) {
        hearsPredicateListeners.add(new AbstractMap.SimpleEntry(predicate, listener));
    }

    /**
     * Registers a hear-listener.
     * This listener will be called once for every message that contains the given pattern.
     * The pattern does not need to match the entire message text, only part of it. <br/>
     * <br/>
     * Multiple matches inside the same message will only result in the message being called once.
     * That is, for a message of {@code "hi hi"} and a pattern of {@code "hi"}, the listener will be called only once. <br/>
     * <br/>
     * To circumnavigate this, you can use the context's matcher in a do-while loop,
     * with the condition as {@code context.getMatcher().find()}.
     *
     * @param pattern the regular expression to find in the message
     * @param listener a listener to be called every time the pattern matches a message
     */
    public void hears(Pattern pattern, TelegrafHeardMessageListener listener) {
        hearsPatternListeners.add(new AbstractMap.SimpleEntry<>(pattern, listener));
    }

    /**
     * Registers a command listener, to be called every time the given command is sent.
     *
     * @param label the command's text
     * @param listener a listener to be called when the command is executed
     */
    public void command(String label, TelegrafCommandListener listener) {
        bot.getCommandRegistry().registerCommand(label.toLowerCase(), (event, command) -> {
            TelegrafCommandContext context = new TelegrafCommandContext(bot, event.getUpdate(), event.getMessage(), command);
            listener.onCommand(context);
            return true;
        });
    }

    /**
     * Registers a {@code /start} command listener, to be called when a user sends the bot {@code /start}.
     *
     * @param listener a listener to be called when {@code /start} is received
     */
    public void start(TelegrafCommandListener listener) {
        command(COMMAND_START, listener);
    }

    /**
     * Registers a {@code /start} command listener, to be called when a user sends the bot {@code /start}.
     *
     * @param startMessageText text to be sent when {@code /start} is received
     */
    public void start(TextBuilder startMessageText) {
        final TextBuilder textBuilder = TextBuilder.create().html(startMessageText.toHtml());
        start(ctx ->
                ctx.getBot().perform(SendText.builder()
                        .chatId(ctx.getMessage().getChat().getChatId())
                        .replyToMessageID(ctx.getMessage().getMessageId())
                        .text(textBuilder)
                        .build()));
    }

    /**
     * Registers a {@code /help} command listener, to be called when a user sends the bot {@code /help}.
     *
     * @param listener a listener to be called when {@code /help} is received
     */
    public void help(TelegrafCommandListener listener) {
        command(COMMAND_HELP, listener);
    }

    /**
     * Registers a {@code /help} command listener, to be called when a user sends the bot {@code /help}.
     *
     * @param helpMessageText text to be sent when {@code /help} is received
     */
    public void help(TextBuilder helpMessageText) {
        final TextBuilder textBuilder = TextBuilder.create().html(helpMessageText.toHtml());
        help(ctx ->
                ctx.getBot().perform(SendText.builder()
                        .chatId(ctx.getMessage().getChat().getChatId())
                        .replyToMessageID(ctx.getMessage().getMessageId())
                        .text(textBuilder)
                        .build()));
    }

    /**
     * Registers a {@code /settings} command listener, to be called when a user sends the bot {@code /settings}.
     *
     * @param listener a listener to be called when {@code /settings} is received
     */
    public void settings(TelegrafCommandListener listener) {
        command(COMMAND_SETTINGS, listener);
    }

    /**
     * Registers a {@code /settings} command listener, to be called when a user sends the bot {@code /settings}.
     *
     * @param settingsMessageText text to be sent when {@code /settings} is received
     */
    public void settings(TextBuilder settingsMessageText) {
        final TextBuilder textBuilder = TextBuilder.create().html(settingsMessageText.toHtml());
        settings(ctx ->
                ctx.getBot().perform(SendText.builder()
                        .chatId(ctx.getMessage().getChat().getChatId())
                        .replyToMessageID(ctx.getMessage().getMessageId())
                        .text(textBuilder)
                        .build()));
    }

    /**
     * Registers a message entity listener, to be called whenever a user sends a text message containing a given message entity.
     *
     * @param entityType the message entity type
     * @param listener a listener to be called when a message entity of the given type is received
     *
     * @param <E> the message entity
     */
    public <E extends MessageEntity<E>>
    void entity(MessageEntityType<E> entityType, TelegrafMessageEntityListener<MessageEntityType<E>, E> listener) {
        //noinspection Duplicates
        messageEntityListeners.compute(
                entityType,
                (k, v) -> {
                    if (v == null) {
                        v = new ArrayList<>();
                    }
                    v.add(listener);
                    return v;
                }
        );
    }

    public void mention(String mention, TelegrafMessageEntityListener<MessageEntityType<MessageEntity.DefaultMessageEntity>, MessageEntity.DefaultMessageEntity> listener) {
        entity(MessageEntityType.MENTION, ctx -> {
            List<MessageEntity.DefaultMessageEntity> entities = ctx.getEntities()
                    .stream()
                    .filter(e -> mention.equalsIgnoreCase(e.getContent()))
                    .collect(Collectors.toList());
            if (!entities.isEmpty()) {
                listener.onMessageEntity(ctx.withEntities(entities));
            }
        });
    }

    public void phone(String phone, TelegrafMessageEntityListener<MessageEntityType<MessageEntity.DefaultMessageEntity>, MessageEntity.DefaultMessageEntity> listener) {
        entity(MessageEntityType.PHONE_NUMBER, ctx -> {
            List<MessageEntity.DefaultMessageEntity> entities = ctx.getEntities()
                    .stream()
                    .filter(e -> phone.equalsIgnoreCase(e.getContent()))
                    .collect(Collectors.toList());
            if (!entities.isEmpty()) {
                listener.onMessageEntity(ctx.withEntities(entities));
            }
        });
    }

    public void hashtag(String hashtag, TelegrafMessageEntityListener<MessageEntityType<MessageEntity.DefaultMessageEntity>, MessageEntity.DefaultMessageEntity> listener) {
        entity(MessageEntityType.HASHTAG, ctx -> {
            List<MessageEntity.DefaultMessageEntity> entities = ctx.getEntities()
                    .stream()
                    .filter(e -> hashtag.equalsIgnoreCase(e.getContent()))
                    .collect(Collectors.toList());
            if (!entities.isEmpty()) {
                listener.onMessageEntity(ctx.withEntities(entities));
            }
        });
    }

    public void cashtag(String cashtag, TelegrafMessageEntityListener<MessageEntityType<MessageEntity.DefaultMessageEntity>, MessageEntity.DefaultMessageEntity> listener) {
        entity(MessageEntityType.CASHTAG, ctx -> {
            List<MessageEntity.DefaultMessageEntity> entities = ctx.getEntities()
                    .stream()
                    .filter(e -> cashtag.equalsIgnoreCase(e.getContent()))
                    .collect(Collectors.toList());
            if (!entities.isEmpty()) {
                listener.onMessageEntity(ctx.withEntities(entities));
            }
        });
    }

}
