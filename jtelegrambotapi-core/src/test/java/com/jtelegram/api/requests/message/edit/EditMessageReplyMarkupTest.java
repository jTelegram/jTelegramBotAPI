package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.SupergroupChat;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.message.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author Nick Robson
 */
public class EditMessageReplyMarkupTest {

    private static final long CHAT_MOCK_ID = 0xC0_FEFE;
    private static final int MESSAGE_MOCK_ID = 0xCAFE_CAB;
    private static final String INLINE_ID = "test_inline_id";

    private static Message messageMock;

    @BeforeAll
    public static void setup() {
        Chat chatMock = spy(SupergroupChat.class);
        when(chatMock.getChatId()).thenReturn(ChatId.of(CHAT_MOCK_ID));

        messageMock = spy(Message.class);
        when(messageMock.getChat()).thenReturn(chatMock);
        when(messageMock.getMessageId()).thenReturn(MESSAGE_MOCK_ID);
    }

    @Test
    public void toEditTextRequest_output_same_as_forMessage() {
        EditMessageReplyMarkup expected = EditMessageReplyMarkup.forMessage(messageMock).build();
        EditMessageReplyMarkup actual = messageMock.toEditReplyMarkupRequest().build();

        assertEquals(expected, actual);
    }

    @Test
    public void builds_with_correct_chatid_and_messageid_text() {
        EditMessageReplyMarkup request = messageMock.toEditReplyMarkupRequest().build();

        assertEquals(CHAT_MOCK_ID, request.getChatId().getId());
        assertEquals(MESSAGE_MOCK_ID, request.getMessageId());
        assertNull(request.getInlineMessageId());
    }

    @Test
    public void builds_with_correct_inlineid() {
        EditMessageReplyMarkup request = EditMessageReplyMarkup.forInlineMessage(INLINE_ID).build();

        assertNull(request.getChatId());
        assertEquals(0, request.getMessageId());
        assertEquals(INLINE_ID, request.getInlineMessageId());
    }

}
