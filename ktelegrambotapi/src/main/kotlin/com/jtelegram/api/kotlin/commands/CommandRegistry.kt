package com.jtelegram.api.kotlin.commands

import com.jtelegram.api.commands.Command
import com.jtelegram.api.commands.filters.CommandFilter
import com.jtelegram.api.events.message.TextMessageEvent
import com.jtelegram.api.kotlin.BotContext
import com.jtelegram.api.kotlin.KTelegramBot
import kotlinx.coroutines.launch

fun suspendCommand(filter: suspend BotContext.(TextMessageEvent, Command) -> Unit) = CommandFilter { event, command ->
    val bot = event.bot

    if (bot !is KTelegramBot) {
        throw IllegalStateException("Suspending command filters can only be used with KTelegramBots!")
    }

    bot.coroutineScope.launch {
        filter.invoke(BotContext(bot), event, command)
    }

    true
}
