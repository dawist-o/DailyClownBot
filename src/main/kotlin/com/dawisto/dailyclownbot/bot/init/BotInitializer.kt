package com.dawisto.dailyclownbot.bot.init

import com.dawisto.dailyclownbot.bot.ClownBot
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@Component
class BotInitializer(
    val clownBot: ClownBot
) {

    @EventListener(classes = [ContextRefreshedEvent::class])
    fun init() {
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        try {
            telegramBotsApi.registerBot(clownBot)
        } catch (e: Exception) {
            println(e)
        }
    }
}