package com.jtelegram.api.kotlin

import com.jtelegram.api.TelegramBot
import com.jtelegram.api.TelegramBotRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher

class KTelegramBot constructor(registry: TelegramBotRegistry?, apiKey: String?) : TelegramBot(registry, apiKey) {
    val coroutineScope = CoroutineScope(eventRegistry.threadPool.asCoroutineDispatcher())
}