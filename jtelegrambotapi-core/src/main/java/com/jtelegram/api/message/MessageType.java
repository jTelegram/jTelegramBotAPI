package com.jtelegram.api.message;

import com.jtelegram.api.events.chat.ChatMemberJoinedEvent;
import com.jtelegram.api.events.chat.ChatMemberLeftEvent;
import com.jtelegram.api.events.chat.ChatMigratedFromChatEvent;
import com.jtelegram.api.events.chat.ChatMigratedToChatEvent;
import com.jtelegram.api.events.chat.ChatPhotoDeletedEvent;
import com.jtelegram.api.events.chat.GroupChatCreatedEvent;
import com.jtelegram.api.events.chat.NewChatPhotoEvent;
import com.jtelegram.api.events.chat.NewChatTitleEvent;
import com.jtelegram.api.events.chat.PinnedMessageEvent;
import com.jtelegram.api.events.chat.SupergroupChatCreatedEvent;
import com.jtelegram.api.events.chat.UserLoggedInEvent;
import com.jtelegram.api.events.message.AnimationMessageEvent;
import com.jtelegram.api.events.message.AudioMessageEvent;
import com.jtelegram.api.events.message.ContactMessageEvent;
import com.jtelegram.api.events.message.DocumentMessageEvent;
import com.jtelegram.api.events.message.GameMessageEvent;
import com.jtelegram.api.events.message.InvoiceMessageEvent;
import com.jtelegram.api.events.message.LocationMessageEvent;
import com.jtelegram.api.events.message.MessageEvent;
import com.jtelegram.api.events.message.PhotoMessageEvent;
import com.jtelegram.api.events.message.StickerMessageEvent;
import com.jtelegram.api.events.message.TextMessageEvent;
import com.jtelegram.api.events.message.VenueMessageEvent;
import com.jtelegram.api.events.message.VideoMessageEvent;
import com.jtelegram.api.events.message.VideoNoteMessageEvent;
import com.jtelegram.api.events.message.VoiceMessageEvent;
import com.jtelegram.api.events.payment.SuccessfulPaymentEvent;
import com.jtelegram.api.message.games.Game;
import com.jtelegram.api.message.impl.AnimationMessage;
import com.jtelegram.api.message.impl.AudioMessage;
import com.jtelegram.api.message.impl.ContactMessage;
import com.jtelegram.api.message.impl.DocumentMessage;
import com.jtelegram.api.message.impl.GameMessage;
import com.jtelegram.api.message.impl.InvoiceMessage;
import com.jtelegram.api.message.impl.LocationMessage;
import com.jtelegram.api.message.impl.PhotoMessage;
import com.jtelegram.api.message.impl.StickerMessage;
import com.jtelegram.api.message.impl.SuccessfulPaymentMessage;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.message.impl.VenueMessage;
import com.jtelegram.api.message.impl.VideoMessage;
import com.jtelegram.api.message.impl.VideoNoteMessage;
import com.jtelegram.api.message.impl.VoiceMessage;
import com.jtelegram.api.message.impl.service.DeleteChatPhotoMessage;
import com.jtelegram.api.message.impl.service.GroupChatCreatedMessage;
import com.jtelegram.api.message.impl.service.LeftChatMemberMessage;
import com.jtelegram.api.message.impl.service.MigrateFromChatIdMessage;
import com.jtelegram.api.message.impl.service.MigrateToChatIdMessage;
import com.jtelegram.api.message.impl.service.NewChatMembersMessage;
import com.jtelegram.api.message.impl.service.NewChatPhotoMessage;
import com.jtelegram.api.message.impl.service.NewChatTitleMessage;
import com.jtelegram.api.message.impl.service.PinnedMessageMessage;
import com.jtelegram.api.message.impl.service.SupergroupChatCreatedMessage;
import com.jtelegram.api.message.impl.service.UserLoggedInMessage;
import com.jtelegram.api.message.media.Animation;
import com.jtelegram.api.message.media.Audio;
import com.jtelegram.api.message.media.Contact;
import com.jtelegram.api.message.media.Document;
import com.jtelegram.api.message.media.Location;
import com.jtelegram.api.message.media.Photo;
import com.jtelegram.api.message.media.Venue;
import com.jtelegram.api.message.media.Video;
import com.jtelegram.api.message.media.VideoNote;
import com.jtelegram.api.message.media.Voice;
import com.jtelegram.api.message.payments.Invoice;
import com.jtelegram.api.message.payments.SuccessfulPayment;
import com.jtelegram.api.message.sticker.Sticker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class MessageType<C, T extends Message<C>, E extends MessageEvent<T>> {
    public static final MessageType<String, TextMessage, TextMessageEvent> TEXT = new MessageType<>("TEXT", String.class, TextMessage.class, TextMessageEvent.class);
    public static final MessageType<Animation, AnimationMessage, AnimationMessageEvent> ANIMATION = new MessageType<>("ANIMATION", Animation.class, AnimationMessage.class, AnimationMessageEvent.class);
    public static final MessageType<Audio, AudioMessage, AudioMessageEvent> AUDIO = new MessageType<>("AUDIO", Audio.class, AudioMessage.class, AudioMessageEvent.class);
    public static final MessageType<Document, DocumentMessage, DocumentMessageEvent> DOCUMENT = new MessageType<>("DOCUMENT", Document.class, DocumentMessage.class, DocumentMessageEvent.class);
    public static final MessageType<Game, GameMessage, GameMessageEvent> GAME = new MessageType<>("GAME", Game.class, GameMessage.class, GameMessageEvent.class);
    public static final MessageType<Photo, PhotoMessage, PhotoMessageEvent> PHOTO = new MessageType<>("PHOTO", Photo.class, PhotoMessage.class, PhotoMessageEvent.class);
    public static final MessageType<Sticker, StickerMessage, StickerMessageEvent> STICKER = new MessageType<>("STICKER", Sticker.class, StickerMessage.class, StickerMessageEvent.class);
    public static final MessageType<Video, VideoMessage, VideoMessageEvent> VIDEO = new MessageType<>("VIDEO", Video.class, VideoMessage.class, VideoMessageEvent.class);
    public static final MessageType<Voice, VoiceMessage, VoiceMessageEvent> VOICE = new MessageType<>("VOICE", Voice.class, VoiceMessage.class, VoiceMessageEvent.class);
    public static final MessageType<VideoNote, VideoNoteMessage, VideoNoteMessageEvent> VIDEO_NOTE = new MessageType<>("VIDEO_NOTE", VideoNote.class, VideoNoteMessage.class, VideoNoteMessageEvent.class);
    public static final MessageType<Contact, ContactMessage, ContactMessageEvent> CONTACT = new MessageType<>("CONTACT", Contact.class, ContactMessage.class, ContactMessageEvent.class);
    public static final MessageType<Venue, VenueMessage, VenueMessageEvent> VENUE = new MessageType<>("VENUE", Venue.class, VenueMessage.class, VenueMessageEvent.class);
    public static final MessageType<Location, LocationMessage, LocationMessageEvent> LOCATION = new MessageType<>("LOCATION", Location.class, LocationMessage.class, LocationMessageEvent.class);
    public static final MessageType<Invoice, InvoiceMessage, InvoiceMessageEvent> INVOICE = new MessageType<>("INVOICE", Invoice.class, InvoiceMessage.class, InvoiceMessageEvent.class);
    public static final MessageType<SuccessfulPayment, SuccessfulPaymentMessage, SuccessfulPaymentEvent> SUCCESSFUL_PAYMENT = new MessageType<>("SUCCESSFUL_PAYMENT", SuccessfulPayment.class, SuccessfulPaymentMessage.class, SuccessfulPaymentEvent.class);

    public static final MessageType<Object, NewChatMembersMessage, ChatMemberJoinedEvent> NEW_CHAT_MEMBERS = new MessageType<>("NEW_CHAT_MEMBERS", Object.class, NewChatMembersMessage.class, ChatMemberJoinedEvent.class);
    public static final MessageType<Object, LeftChatMemberMessage, ChatMemberLeftEvent> LEFT_CHAT_MEMBER = new MessageType<>("LEFT_CHAT_MEMBER", Object.class, LeftChatMemberMessage.class, ChatMemberLeftEvent.class);
    public static final MessageType<Object, NewChatTitleMessage, NewChatTitleEvent> NEW_CHAT_TITLE = new MessageType<>("NEW_CHAT_TITLE", Object.class, NewChatTitleMessage.class, NewChatTitleEvent.class);
    public static final MessageType<Object, NewChatPhotoMessage, NewChatPhotoEvent> NEW_CHAT_PHOTO = new MessageType<>("NEW_CHAT_PHOTO", Object.class, NewChatPhotoMessage.class, NewChatPhotoEvent.class);
    public static final MessageType<Object, DeleteChatPhotoMessage, ChatPhotoDeletedEvent> DELETE_CHAT_PHOTO = new MessageType<>("DELETE_CHAT_PHOTO", Object.class, DeleteChatPhotoMessage.class, ChatPhotoDeletedEvent.class);
    public static final MessageType<Object, GroupChatCreatedMessage, GroupChatCreatedEvent> GROUP_CHAT_CREATED = new MessageType<>("GROUP_CHAT_CREATED", Object.class, GroupChatCreatedMessage.class, GroupChatCreatedEvent.class);
    public static final MessageType<Object, PinnedMessageMessage, PinnedMessageEvent> PINNED_MESSAGE = new MessageType<>("PINNED_MESSAGE", Object.class, PinnedMessageMessage.class, PinnedMessageEvent.class);
    public static final MessageType<Object, UserLoggedInMessage, UserLoggedInEvent> CONNECTED_WEBSITE = new MessageType<>("CONNECTED_WEBSITE", Object.class, UserLoggedInMessage.class, UserLoggedInEvent.class);
    public static final MessageType<Object, SupergroupChatCreatedMessage, SupergroupChatCreatedEvent> SUPERGROUP_CHAT_CREATED = new MessageType<>("SUPERGROUP_CHAT_CREATED", Object.class, SupergroupChatCreatedMessage.class, SupergroupChatCreatedEvent.class);
    public static final MessageType<Object, MigrateToChatIdMessage, ChatMigratedToChatEvent> MIGRATE_TO_CHAT_ID = new MessageType<>("MIGRATE_TO_CHAT_ID", Object.class, MigrateToChatIdMessage.class, ChatMigratedToChatEvent.class);
    public static final MessageType<Object, MigrateFromChatIdMessage, ChatMigratedFromChatEvent> MIGRATE_FROM_CHAT_ID = new MessageType<>("MIGRATE_FROM_CHAT_ID", Object.class, MigrateFromChatIdMessage.class, ChatMigratedFromChatEvent.class);

    public static final MessageType<?, ?, ?>[] values = {
            TEXT, ANIMATION, AUDIO, DOCUMENT, GAME, PHOTO, STICKER, VIDEO, VOICE, VIDEO_NOTE, CONTACT, VENUE, LOCATION, INVOICE, SUCCESSFUL_PAYMENT,
            NEW_CHAT_MEMBERS, LEFT_CHAT_MEMBER, NEW_CHAT_TITLE, NEW_CHAT_PHOTO, DELETE_CHAT_PHOTO, GROUP_CHAT_CREATED, PINNED_MESSAGE, CONNECTED_WEBSITE, SUPERGROUP_CHAT_CREATED, MIGRATE_TO_CHAT_ID, MIGRATE_FROM_CHAT_ID };

    private String name;
    private Class<C> messageContentsClass;
    private Class<T> messageClass;
    private Class<E> receiveEventClass;

    public static MessageType typeFrom(Class<? extends Message> clazz) {
        for (MessageType type : values) {
            //noinspection unchecked
            if (type.getMessageClass().isAssignableFrom(clazz)) {
                return type;
            }
        }

        return null;
    }

    public static <T extends Message> MessageType typeFrom(T message) {
        return typeFrom(message.getClass());
    }
}
