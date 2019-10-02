package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.events.message.MessageEvent;
import com.jtelegram.api.events.message.TextMessageEvent;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.MessageType;
import com.jtelegram.api.message.entity.MessageEntity;
import com.jtelegram.api.message.entity.MessageEntityType;
import com.jtelegram.api.message.entity.TextLinkMessageEntity;
import com.jtelegram.api.message.entity.TextMentionMessageEntity;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.message.send.SendText;
import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateContents;
import com.jtelegram.api.update.UpdateType;
import com.jtelegram.api.util.TextBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A wrapper around {@link TelegramBot} allowing usage of the jTelegramBotAPI in the same style as that of
 * <a href="https://telegraf.js.org">telegraf</a>, a popular Telegram Bot API for JavaScript.
 *
 * @author Nick Robson
 */
@ParametersAreNonnullByDefault
public class TelegrafBot {

    /**
     * Corresponds to the Telegram /start command
     */
    public static final String COMMAND_START = "start";

    /**
     * Corresponds to the Telegram /help command
     */
    public static final String COMMAND_HELP = "help";

    /**
     * Corresponds to the Telegram /settings command
     */
    public static final String COMMAND_SETTINGS = "settings";

    /**
     * Tracks update listeners. i.e. listeners that are notified for different types of {@link Update updates}
     */
    private final Map<Class<? extends Update>, List<TelegrafUpdateListener<?, ?, ?>>> updateListeners = new HashMap<>();

    /**
     * Tracks message listeners. i.e. listeners that are notified for different types of {@link Message messages}
     */
    private final Map<Class<? extends Message>, List<TelegrafMessageListener<?, ?, ?>>> messageListeners = new HashMap<>();

    /**
     * Tracks message entity listeners. i.e. listeners that are notified for different
     * {@link MessageEntityType types} of {@link MessageEntity message entity}
     */
    private final Map<MessageEntityType, List<TelegrafMessageEntityListener<?, ?>>> messageEntityListeners = new HashMap<>();

    /**
     * Tracks listeners that are notified when certain strings are mentioned in text messages
     */
    private final Map<String, List<TelegrafMessageListener<?, TextMessage, String>>> hearsTextListeners = new HashMap<>();

    /**
     * Tracks listeners that are notified when their corresponding predicates match the text of a text message
     */
    private final Map<Predicate<String>, List<TelegrafMessageListener<?, TextMessage, String>>> hearsPredicateListeners = new HashMap<>();

    /**
     * Tracks listeners that are notified when their corresponding patterns match the text of a text message
     */
    private final Map<Pattern, List<TelegrafHeardMessageListener>> hearsPatternListeners = new HashMap<>();

    @Nonnull
    private final TelegramBot bot;

    @SuppressWarnings("unchecked")
    public TelegrafBot(@Nonnull TelegramBot bot) {
        this.bot = bot;
        // register a general listener that will be called for all events that extend UpdateEvent
        bot.getEventRegistry().registerEvent(UpdateEvent.class, event -> {
            Update<?> update = event.getUpdate();
            Class<? extends Update> updateClass = update.getClass();
            // e.g. if the Update is a MessageUpdate this will get all listeners that want to see MessageUpdates
            List<TelegrafUpdateListener<?, ?, ?>> listeners = updateListeners.get(updateClass);
            if (listeners != null) {
                // create an update context for the update and propagate it to all listeners that want to listen to that type of update
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
        // register a general listener that will be called for all events that extend MessageEvent
        bot.getEventRegistry().registerEvent(MessageEvent.class, event -> {
            Update.MessageUpdate update = (Update.MessageUpdate) event.getUpdate();
            Message<?> message = update.getContents();
            Class<? extends Message> messageClass = message.getClass();
            // e.g. if the Message is a TextMessage this will get all listeners that want to see text messages
            List<TelegrafMessageListener<?, ?, ?>> listeners = messageListeners.get(messageClass);
            if (listeners != null) {
                // create an message context for the message and propagate it to all listeners that want to listen to that type of message
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
        // register a general listener that will be called for all events that extend TextMessageEvent
        bot.getEventRegistry().registerEvent(TextMessageEvent.class, event -> {
            Update.MessageUpdate update = event.getUpdate();
            TextMessage message = event.getMessage();

            // create an message context for the message
            TelegrafMessageContext context = new TelegrafMessageContext(bot, update, message);
            String text = message.getText();
            hearsTextListeners.forEach((k, v) -> {
                try {
                    // call the listener if the message contains the desired text
                    if (text.contains(k)) {
                        v.forEach(l -> l.onMessage(context));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            hearsPredicateListeners.forEach((k, v) -> {
                try {
                    // if the listener's predicate matches, call the listener
                    if (k.test(text)) {
                        v.forEach(l -> l.onMessage(context));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            hearsPatternListeners.forEach((k, v) -> {
                try {
                    Matcher matcher = k.matcher(text);
                    // call the listeners once each, if the text contains 1 or more matches
                    if (matcher.find()) {
                        TelegrafHeardMessageContext ctx = new TelegrafHeardMessageContext(bot, update, message, matcher);
                        v.forEach(l -> {
                            l.onMessage(ctx);
                            // reset() and find() reset it so if there are multiple listeners registered with the same
                            // pattern they won't interfere with each other (i.e. one consuming all the pattern matches
                            // won't screw over the other one)
                            matcher.reset();
                            matcher.find();
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            // collect all message entities into lists by the type of entity they are
            // e.g. "@abc @def" would be grouped into a List<"abc", "def"> under the type of MessageEntityType.MENTION
            Map<MessageEntityType, List<MessageEntity>> entities = new HashMap<>();
            event.getMessage().getEntities().forEach(e ->
                entities.computeIfAbsent(e.getType(), $ -> new ArrayList<>()).add(e)
            );
            // for each (Type, List<Entity>) tuple, get all listeners for each type
            // and call them with all the entities of that type
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
     * @param <UpdateContext> the update context type
     */
    public <U extends UpdateContents, T extends Update<U>, UpdateContext extends TelegrafUpdateContext<UpdateContext, T, U>>
    void on(UpdateType<T> updateType, TelegrafUpdateListener<UpdateContext, T, U> listener) {
        Class<? extends Update> updateClass = updateType.getUpdateClass();

        //noinspection Duplicates
        updateListeners.computeIfAbsent(updateClass, $ -> new ArrayList<>()).add(listener);
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
     * @param <MessageContext> the message context type
     */
    public <C, T extends Message<C>, E extends MessageEvent<T>, MessageContext extends TelegrafMessageContext<MessageContext, T, C>>
    void on(MessageType<C, T, E> messageType, TelegrafMessageListener<MessageContext, T, C> listener) {
        Class<? extends Message> messageClass = messageType.getMessageClass();

        //noinspection Duplicates
        messageListeners.computeIfAbsent(messageClass, $ -> new ArrayList<>()).add(listener);
    }

    /**
     * Registers a hear-listener.
     * This listener will be called once for every message that contains the given text. <br>
     *
     * @param text the text the message must contain
     * @param listener a listener to be called every time a message contains the given text
     */
    @SuppressWarnings("unchecked")
    public void hears(String text, TelegrafMessageListener<? extends TelegrafMessageContext<?, TextMessage, String>, TextMessage, String> listener) {
        //noinspection Duplicates
        hearsTextListeners.computeIfAbsent(text, $ -> new ArrayList<>()).add(listener);
    }

    /**
     * Registers a hear-listener.
     * This listener will be called once for every message for which the predicate returns true. <br>
     *
     * @param predicate the predicate to use to determine if the listener should be called
     * @param listener a listener to be called every time the predicate returns true on a message
     */
    @SuppressWarnings("unchecked")
    public void hears(Predicate<String> predicate, TelegrafMessageListener<? extends TelegrafMessageContext<?, TextMessage, String>, TextMessage, String> listener) {
        //noinspection Duplicates
        hearsPredicateListeners.computeIfAbsent(predicate, $ -> new ArrayList<>()).add(listener);
    }

    /**
     * Registers a hear-listener.
     * This listener will be called once for every message that contains the given pattern.
     * The pattern does not need to match the entire message text, only part of it. <br>
     * <br>
     * Multiple matches inside the same message will only result in the message being called once.
     * That is, for a message of {@code "hi hi"} and a pattern of {@code "hi"}, the listener will be called only once. <br>
     * <br>
     * To circumnavigate this, you can use the context's matcher in a do-while loop,
     * with the condition as {@code context.getMatcher().find()}.
     *
     * @param pattern the regular expression to find in the message
     * @param listener a listener to be called every time the pattern matches a message
     */
    public void hears(Pattern pattern, TelegrafHeardMessageListener listener) {
        hearsPatternListeners.computeIfAbsent(pattern, $ -> new ArrayList<>()).add(listener);
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
        messageEntityListeners.computeIfAbsent(entityType, $ -> new ArrayList<>()).add(listener);
    }

    /**
     * Registers a mention listener, to be called whenever a user sends a text message containing a specific {@link MessageEntityType#MENTION}.
     *
     * @param mention the mention to listen for (case insensitive)
     * @param listener a listener to be called when a matching mention is received
     */
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

    /**
     * Registers a phone number listener, to be called whenever a user sends a text message containing a specific {@link MessageEntityType#PHONE_NUMBER}.
     *
     * @param phone the phone number to listen for (case insensitive)
     * @param listener a listener to be called when a matching phone number is received
     */
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

    /**
     * Registers a email listener, to be called whenever a user sends a text message containing a specific {@link MessageEntityType#EMAIL}.
     *
     * @param email the email to listen for (case insensitive)
     * @param listener a listener to be called when a matching email is received
     */
    public void email(String email, TelegrafMessageEntityListener<MessageEntityType<MessageEntity.DefaultMessageEntity>, MessageEntity.DefaultMessageEntity> listener) {
        entity(MessageEntityType.EMAIL, ctx -> {
            List<MessageEntity.DefaultMessageEntity> entities = ctx.getEntities()
                    .stream()
                    .filter(e -> email.equalsIgnoreCase(e.getContent()))
                    .collect(Collectors.toList());
            if (!entities.isEmpty()) {
                listener.onMessageEntity(ctx.withEntities(entities));
            }
        });
    }

    /**
     * Registers a hashtag listener, to be called whenever a user sends a text message containing a specific {@link MessageEntityType#HASHTAG}.
     *
     * @param hashtag the hashtag to listen for (case insensitive)
     * @param listener a listener to be called when a matching hashtag is received
     */
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

    /**
     * Registers a cashtag listener, to be called whenever a user sends a text message containing a specific {@link MessageEntityType#CASHTAG}.
     *
     * @param cashtag the cashtag to listen for (case insensitive)
     * @param listener a listener to be called when a matching cashtag is received
     */
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

    /**
     * Registers a URL listener, to be called whenever a user sends a text message containing a specific {@link MessageEntityType#URL}.
     *
     * @param url the URL to listen for (case insensitive)
     * @param listener a listener to be called when a matching URL is received
     */
    public void url(String url, TelegrafMessageEntityListener<MessageEntityType<MessageEntity.DefaultMessageEntity>, MessageEntity.DefaultMessageEntity> listener) {
        entity(MessageEntityType.URL, ctx -> {
            List<MessageEntity.DefaultMessageEntity> entities = ctx.getEntities()
                    .stream()
                    .filter(e -> url.equalsIgnoreCase(e.getContent()))
                    .collect(Collectors.toList());
            if (!entities.isEmpty()) {
                listener.onMessageEntity(ctx.withEntities(entities));
            }
        });
    }

    /**
     * Registers a text link listener, to be called whenever a user sends a text message containing a specific {@link MessageEntityType#TEXT_LINK URL}.
     *
     * @param url the URL to listen for (case insensitive)
     * @param listener a listener to be called when a matching URL is received
     */
    public void textLink(String url, TelegrafMessageEntityListener<MessageEntityType<TextLinkMessageEntity>, TextLinkMessageEntity> listener) {
        entity(MessageEntityType.TEXT_LINK, ctx -> {
            List<TextLinkMessageEntity> entities = ctx.getEntities()
                    .stream()
                    .filter(e -> url.equalsIgnoreCase(e.getUrl()))
                    .collect(Collectors.toList());
            if (!entities.isEmpty()) {
                listener.onMessageEntity(ctx.withEntities(entities));
            }
        });
    }

    /**
     * Registers a text mention listener, to be called whenever a user sends a text message containing a {@link MessageEntityType#TEXT_MENTION text mention} to a specific user.
     *
     * @param userId the user id to listen for (case insensitive)
     * @param listener a listener to be called when a matching mention is received
     */
    public void textMention(long userId, TelegrafMessageEntityListener<MessageEntityType<TextMentionMessageEntity>, TextMentionMessageEntity> listener) {
        entity(MessageEntityType.TEXT_MENTION, ctx -> {
            List<TextMentionMessageEntity> entities = ctx.getEntities()
                    .stream()
                    .filter(e -> userId == e.getUser().getId())
                    .collect(Collectors.toList());
            if (!entities.isEmpty()) {
                listener.onMessageEntity(ctx.withEntities(entities));
            }
        });
    }

}
