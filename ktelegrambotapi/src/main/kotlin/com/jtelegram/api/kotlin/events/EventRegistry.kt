package com.jtelegram.api.kotlin.events

import com.jtelegram.api.events.Event
import com.jtelegram.api.events.EventRegistry
import com.jtelegram.api.kotlin.BotContext
import com.jtelegram.api.kotlin.KTelegramBot
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

fun <E : Event> EventRegistry.on(eventType: KClass<E>, listener: suspend BotContext.(E) -> Unit) {
    val bot = this.bot

    if (bot !is KTelegramBot) {
        throw IllegalStateException("Suspending listeners can only be used with KTelegramBots!")
    }

    registerEvent(eventType.java) { event ->
        bot.coroutineScope.launch {
            listener.invoke(BotContext(bot), event)
        }
    }
}
