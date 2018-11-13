package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.SupergroupChat;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.message.impl.AudioMessage;
import com.jtelegram.api.message.impl.DocumentMessage;
import com.jtelegram.api.message.impl.PhotoMessage;
import com.jtelegram.api.message.impl.VideoMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author Nick Robson
 */
public class EditMessageMediaTest {

    private static final long CHAT_MOCK_ID = 0xC0_FEFE;
    private static final int AUDIO_MESSAGE_MOCK_ID = 0xCAFE_CAB0;
    private static final int DOCUMENT_MESSAGE_MOCK_ID = 0xCAFE_CAB1;
    private static final int PHOTO_MESSAGE_MOCK_ID = 0xCAFE_CAB2;
    private static final int VIDEO_MESSAGE_MOCK_ID = 0xCAFE_CAB3;
    private static final String INLINE_ID = "test_inline_id";

    private static AudioMessage audioMessageMock;
    private static DocumentMessage documentMessageMock;
    private static PhotoMessage photoMessageMock;
    private static VideoMessage videoMessageMock;

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
    }

    @Test
    public void toEditMediaRequest_output_same_as_forMessage_audio() {
        EditMessageMedia expected = EditMessageMedia.forMessage(audioMessageMock).build();
        EditMessageMedia actual = audioMessageMock.toEditMediaRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void toEditMediaRequest_output_same_as_forMessage_document() {
        EditMessageMedia expected = EditMessageMedia.forMessage(documentMessageMock).build();
        EditMessageMedia actual = documentMessageMock.toEditMediaRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void toEditMediaRequest_output_same_as_forMessage_photo() {
        EditMessageMedia expected = EditMessageMedia.forMessage(photoMessageMock).build();
        EditMessageMedia actual = photoMessageMock.toEditMediaRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void toEditMediaRequest_output_same_as_forMessage_video() {
        EditMessageMedia expected = EditMessageMedia.forMessage(videoMessageMock).build();
        EditMessageMedia actual = videoMessageMock.toEditMediaRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_audio() {
        EditMessageMedia request = audioMessageMock.toEditMediaRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(AUDIO_MESSAGE_MOCK_ID, request.getMessageId().intValue());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_document() {
        EditMessageMedia request = documentMessageMock.toEditMediaRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(DOCUMENT_MESSAGE_MOCK_ID, request.getMessageId().intValue());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_photo() {
        EditMessageMedia request = photoMessageMock.toEditMediaRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(PHOTO_MESSAGE_MOCK_ID, request.getMessageId().intValue());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_video() {
        EditMessageMedia request = videoMessageMock.toEditMediaRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(VIDEO_MESSAGE_MOCK_ID, request.getMessageId().intValue());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_inlineid() {
        EditMessageMedia request = EditMessageMedia.forInlineMessage(INLINE_ID).build();

        assertNull(request.getChatId());
        assertNull(request.getMessageId());
        assertEquals(INLINE_ID, request.getInlineMessageId());
    }

}
