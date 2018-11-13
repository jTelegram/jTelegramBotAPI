package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.SupergroupChat;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.message.impl.AudioMessage;
import com.jtelegram.api.message.impl.DocumentMessage;
import com.jtelegram.api.message.impl.PhotoMessage;
import com.jtelegram.api.message.impl.VideoMessage;
import com.jtelegram.api.message.impl.VideoNoteMessage;
import com.jtelegram.api.message.impl.VoiceMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author Nick Robson
 */
public class EditMessageCaptionTest {

    private static final long CHAT_MOCK_ID = 0xC0_FEFE;
    private static final int AUDIO_MESSAGE_MOCK_ID = 0xCAFE_CAB0;
    private static final int DOCUMENT_MESSAGE_MOCK_ID = 0xCAFE_CAB1;
    private static final int PHOTO_MESSAGE_MOCK_ID = 0xCAFE_CAB2;
    private static final int VIDEO_MESSAGE_MOCK_ID = 0xCAFE_CAB3;
    private static final int VIDEO_NOTE_MESSAGE_MOCK_ID = 0xCAFE_CAB4;
    private static final int VOICE_MESSAGE_MOCK_ID = 0xCAFE_CAB5;
    private static final String INLINE_ID = "test_inline_id";

    private static AudioMessage audioMessageMock;
    private static DocumentMessage documentMessageMock;
    private static PhotoMessage photoMessageMock;
    private static VideoMessage videoMessageMock;
    private static VideoNoteMessage videoNoteMessageMock;
    private static VoiceMessage voiceMessageMock;

    @BeforeAll
    public static void setup() {
        Chat chatMock = spy(SupergroupChat.class);
        when(chatMock.getChatId()).thenReturn(ChatId.of(CHAT_MOCK_ID));

        audioMessageMock = spy(AudioMessage.class);
        when(audioMessageMock.getChat()).thenReturn(chatMock);
        when(audioMessageMock.getMessageId()).thenReturn(AUDIO_MESSAGE_MOCK_ID);

        documentMessageMock = spy(DocumentMessage.class);
        when(documentMessageMock.getChat()).thenReturn(chatMock);
        when(documentMessageMock.getMessageId()).thenReturn(DOCUMENT_MESSAGE_MOCK_ID);

        photoMessageMock = spy(PhotoMessage.class);
        when(photoMessageMock.getChat()).thenReturn(chatMock);
        when(photoMessageMock.getMessageId()).thenReturn(PHOTO_MESSAGE_MOCK_ID);

        videoMessageMock = spy(VideoMessage.class);
        when(videoMessageMock.getChat()).thenReturn(chatMock);
        when(videoMessageMock.getMessageId()).thenReturn(VIDEO_MESSAGE_MOCK_ID);

        videoNoteMessageMock = spy(VideoNoteMessage.class);
        when(videoNoteMessageMock.getChat()).thenReturn(chatMock);
        when(videoNoteMessageMock.getMessageId()).thenReturn(VIDEO_NOTE_MESSAGE_MOCK_ID);

        voiceMessageMock = spy(VoiceMessage.class);
        when(voiceMessageMock.getChat()).thenReturn(chatMock);
        when(voiceMessageMock.getMessageId()).thenReturn(VOICE_MESSAGE_MOCK_ID);
    }

    @Test
    public void toEditCaptionRequest_output_same_as_forMessage_audio() {
        EditMessageCaption expected = EditMessageCaption.forMessage(audioMessageMock).build();
        EditMessageCaption actual = audioMessageMock.toEditCaptionRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void toEditCaptionRequest_output_same_as_forMessage_document() {
        EditMessageCaption expected = EditMessageCaption.forMessage(documentMessageMock).build();
        EditMessageCaption actual = documentMessageMock.toEditCaptionRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void toEditCaptionRequest_output_same_as_forMessage_photo() {
        EditMessageCaption expected = EditMessageCaption.forMessage(photoMessageMock).build();
        EditMessageCaption actual = photoMessageMock.toEditCaptionRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void toEditCaptionRequest_output_same_as_forMessage_video() {
        EditMessageCaption expected = EditMessageCaption.forMessage(videoMessageMock).build();
        EditMessageCaption actual = videoMessageMock.toEditCaptionRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void toEditCaptionRequest_output_same_as_forMessage_video_note() {
        EditMessageCaption expected = EditMessageCaption.forMessage(videoNoteMessageMock).build();
        EditMessageCaption actual = videoNoteMessageMock.toEditCaptionRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void toEditCaptionRequest_output_same_as_forMessage_voice() {
        EditMessageCaption expected = EditMessageCaption.forMessage(voiceMessageMock).build();
        EditMessageCaption actual = voiceMessageMock.toEditCaptionRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_audio() {
        EditMessageCaption request = audioMessageMock.toEditCaptionRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(AUDIO_MESSAGE_MOCK_ID, request.getMessageId());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_document() {
        EditMessageCaption request = documentMessageMock.toEditCaptionRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(DOCUMENT_MESSAGE_MOCK_ID, request.getMessageId());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_photo() {
        EditMessageCaption request = photoMessageMock.toEditCaptionRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(PHOTO_MESSAGE_MOCK_ID, request.getMessageId());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_video() {
        EditMessageCaption request = videoMessageMock.toEditCaptionRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(VIDEO_MESSAGE_MOCK_ID, request.getMessageId());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_video_note() {
        EditMessageCaption request = videoNoteMessageMock.toEditCaptionRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(VIDEO_NOTE_MESSAGE_MOCK_ID, request.getMessageId());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_voice() {
        EditMessageCaption request = voiceMessageMock.toEditCaptionRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(VOICE_MESSAGE_MOCK_ID, request.getMessageId());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_inlineid() {
        EditMessageCaption request = EditMessageCaption.forInlineMessage(INLINE_ID).build();

        assertNull(request.getChatId());
        assertEquals(0, request.getMessageId());
        assertEquals(INLINE_ID, request.getInlineMessageId());
    }

}
