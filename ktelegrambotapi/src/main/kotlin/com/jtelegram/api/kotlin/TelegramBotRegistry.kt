package com.jtelegram.api.kotlin

import com.jtelegram.api.TelegramBotRegistry
import com.jtelegram.api.requests.GetMe

suspend fun TelegramBotRegistry.registerBot(key: String): KTelegramBot {
    val bot = KTelegramBot(this, key)

    bot.botInfo = bot.execute(GetMe.builder().build())

    bots.add(bot)
    updateProvider.listenFor(bot)

    return bot
}
