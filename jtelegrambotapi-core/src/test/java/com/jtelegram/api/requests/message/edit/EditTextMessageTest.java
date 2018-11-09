package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.SupergroupChat;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.message.impl.GameMessage;
import com.jtelegram.api.message.impl.TextMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author Nick Robson
 */
public class EditTextMessageTest {

    private static final long CHAT_MOCK_ID = 0xC0_FEFE;
    private static final int TEXT_MESSAGE_MOCK_ID = 0xCAFE_CAB0;
    private static final int GAME_MESSAGE_MOCK_ID = 0xCAFE_CAB1;
    private static final String INLINE_ID = "test_inline_id";

    private static TextMessage textMessageMock;
    private static GameMessage gameMessageMock;

    @BeforeAll
    public static void setup() {
        Chat chatMock = spy(SupergroupChat.class);
        when(chatMock.getChatId()).thenReturn(ChatId.of(CHAT_MOCK_ID));

        textMessageMock = spy(TextMessage.class);
        when(textMessageMock.getChat()).thenReturn(chatMock);
        when(textMessageMock.getMessageId()).thenReturn(TEXT_MESSAGE_MOCK_ID);

        gameMessageMock = spy(GameMessage.class);
        when(gameMessageMock.getChat()).thenReturn(chatMock);
        when(gameMessageMock.getMessageId()).thenReturn(GAME_MESSAGE_MOCK_ID);
    }

    @Test
    public void toEditTextRequest_output_same_as_forMessage_text() {
        EditTextMessage expected = EditTextMessage.forMessage(textMessageMock).build();
        EditTextMessage actual = textMessageMock.toEditTextRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void toEditTextRequest_output_same_as_forMessage_game() {
        EditTextMessage expected = EditTextMessage.forMessage(gameMessageMock).build();
        EditTextMessage actual = gameMessageMock.toEditTextRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_text() {
        EditTextMessage request = textMessageMock.toEditTextRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(TEXT_MESSAGE_MOCK_ID, request.getMessageId());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_game() {
        EditTextMessage request = gameMessageMock.toEditTextRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(GAME_MESSAGE_MOCK_ID, request.getMessageId());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_inlineid() {
        EditTextMessage request = EditTextMessage.forInlineMessage(INLINE_ID).build();

        assertNull(request.getChatId());
        assertEquals(0, request.getMessageId());
        assertEquals(INLINE_ID, request.getInlineMessageId());
    }

}
