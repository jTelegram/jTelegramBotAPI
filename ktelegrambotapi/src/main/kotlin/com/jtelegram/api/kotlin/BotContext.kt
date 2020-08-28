package com.jtelegram.api.kotlin

import com.jtelegram.api.chat.Chat
import com.jtelegram.api.chat.id.ChatId
import com.jtelegram.api.message.Message
import com.jtelegram.api.message.impl.TextMessage
import com.jtelegram.api.requests.message.framework.ParseMode
import com.jtelegram.api.requests.message.framework.req.SendableChatRequest
import com.jtelegram.api.requests.message.framework.req.SendableMessageRequest
import com.jtelegram.api.requests.message.send.SendText
import com.jtelegram.api.user.User

class BotContext(val bot: KTelegramBot) {
    private suspend fun <T> sendText(chatId: ChatId<T>, text: String, parseMode: ParseMode): TextMessage {
        return bot.execute (
                SendText.builder()
                        .chatId(chatId)
                        .text(text)
                        .parseMode(parseMode)
                        .build()
        )
    }

    suspend fun Chat.sendText(text: String, parseMode: ParseMode = ParseMode.MARKDOWN): TextMessage {
        return sendText(chatId, text, parseMode)
    }

    suspend fun <ST, S: Message<ST>> Chat.sendMessage(request: SendableMessageRequest<S>): S {
        return sendAction(request)
    }

    suspend fun <S> Chat.sendAction(request: SendableChatRequest<S>): S {
        val chatIdO = chatId

        return bot.execute (
                request.apply {
                    chatId = chatIdO
                }
        )
    }

    suspend fun User.sendText(text: String, parseMode: ParseMode = ParseMode.MARKDOWN): TextMessage {
        return sendText(ChatId.of(id), text, parseMode)
    }

    suspend fun <ST, S: Message<ST>> User.sendMessage(request: SendableMessageRequest<S>): S {
        return sendAction(request)
    }

    suspend fun <S> User.sendAction(request: SendableChatRequest<S>): S {
        return bot.execute (
                 request.apply {
                     chatId = ChatId.of(id)
                 }
        )
    }
}