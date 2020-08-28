package com.jtelegram.api.kotlin.events.message

import com.jtelegram.api.events.message.MessageEvent
import com.jtelegram.api.kotlin.execute
import com.jtelegram.api.message.Message
import com.jtelegram.api.message.impl.TextMessage
import com.jtelegram.api.requests.message.framework.ParseMode
import com.jtelegram.api.requests.message.framework.req.SendableChatRequest
import com.jtelegram.api.requests.message.framework.req.SendableMessageRequest
import com.jtelegram.api.requests.message.send.SendText
import com.jtelegram.api.util.TextBuilder

suspend fun <T, M: Message<T>> MessageEvent<M>.replyWith(
        text: String,
        replyToMessage: Boolean = false,
        parseMode: ParseMode = ParseMode.MARKDOWN
): TextMessage {
    return replyWithMessage (
            SendText.builder()
                    .text(text)
                    .parseMode(parseMode)
                    .build(),
            replyToMessage
    )
}

suspend fun <T, M: Message<T>> MessageEvent<M>.replyWith(
        text: TextBuilder,
        replyToMessage: Boolean = false
): TextMessage {
    return replyWith(text.toHtml(), replyToMessage, ParseMode.HTML)
}

suspend fun <T, M: Message<T>, ST, S: Message<ST>> MessageEvent<M>.replyWithMessage(
        request: SendableMessageRequest<S>,
        replyToMessage: Boolean = false
): S {
    return replyWithAction (
            request.apply {
                if (replyToMessage) {
                    replyToMessageId = message.messageId
                }
            }
    )
}

suspend fun <T, M: Message<T>, S> MessageEvent<M>.replyWithAction(
        request: SendableChatRequest<S>
): S {
    return bot.execute (
            request.apply {
                chatId = message.chat.chatId
            }
    )
}
