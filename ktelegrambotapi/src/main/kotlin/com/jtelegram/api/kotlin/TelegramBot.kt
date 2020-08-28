package com.jtelegram.api.kotlin

import com.jtelegram.api.TelegramBot
import com.jtelegram.api.requests.framework.AbstractTelegramRequest
import com.jtelegram.api.requests.framework.QueryTelegramRequest
import com.jtelegram.api.requests.framework.UpdateTelegramRequest
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun <T> TelegramBot.execute(request: QueryTelegramRequest<T>): T = suspendCoroutine { cont ->
    request.useContinuationForErrors(cont)

    request.setCallback {
        cont.resume(it)
    }

    perform(request)
}

suspend fun TelegramBot.execute(request: UpdateTelegramRequest) = suspendCoroutine<Unit?> { cont ->
    request.useContinuationForErrors(cont)

    request.setCallback {
        cont.resume(null)
    }

    perform(request)
}

private fun <T> AbstractTelegramRequest.useContinuationForErrors(cont: Continuation<T>) {
    setErrorHandler {
        cont.resumeWithException(it)
    }
}