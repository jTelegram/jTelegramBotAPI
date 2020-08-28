package com.jtelegram.api.kotlin.events.inline

import com.jtelegram.api.events.inline.InlineQueryEvent
import com.jtelegram.api.inline.result.framework.InlineResult
import com.jtelegram.api.kotlin.execute
import com.jtelegram.api.requests.inline.AnswerInlineQuery

suspend fun <T: InlineResult> InlineQueryEvent.answer (
        results: List<T>,
        cacheTime: Int? = null,
        isPersonal: Boolean? = null,
        nextOffset: String? = null,
        switchPmText: String? = null,
        switchPmParameter: String? = null
) {
    bot.execute (
            AnswerInlineQuery.builder()
                    .queryId(query.id)
                    .results(results)
                    .cacheTime(cacheTime)
                    .isPersonal(isPersonal)
                    .nextOffset(nextOffset)
                    .switchPmText(switchPmText)
                    .switchPmParameter(switchPmParameter)
                    .build()
    )
}