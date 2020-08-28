package com.jtelegram.api.kotlin.util

import com.jtelegram.api.util.TextBuilder

fun textBuilder(init: KTextBuilder.() -> Unit): KTextBuilder {
    val builder = KTextBuilder()
    builder.init()
    return builder
}

class KTextBuilder: TextBuilder() {
    operator fun String.unaryPlus() {
        plain(this)
    }
}