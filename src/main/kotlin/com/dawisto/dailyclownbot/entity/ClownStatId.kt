package com.dawisto.dailyclownbot.entity

import java.io.Serializable

data class ClownStatId(
    val chatId: Long = 0L,
    val userName: String = ""
): Serializable
