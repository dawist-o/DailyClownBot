package com.dawisto.dailyclownbot.bot

import com.dawisto.dailyclownbot.bot.config.BotConfig
import com.dawisto.dailyclownbot.service.ClownStatService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class ClownBot(
    val config: BotConfig,
    val clownStatService: ClownStatService
) : TelegramLongPollingBot(config.botToken) {


    override fun getBotUsername() = config.botName

    override fun onUpdateReceived(update: Update?) {
        if (update == null || !update.hasMessage())
            return
        update.message.text?.let {
            if (it.lowercase().startsWith("/help"))
                sendHelp(update.message.chatId)
            if (it.lowercase().startsWith("/stat"))
                sendCurrentStat(update.message.chatId)
        }
        clownStatService.handleUpdate(update, botUsername)
    }

    /**
     * CRON format:
     * second, minute, hour, day of month, month, weekday.
     */
    @Scheduled(cron = "0 0 22 * * FRI")
    fun sendDailyClown() {
        clownStatService.sendClownNotifications().forEach { execute(it) }
    }

    fun sendHelp(chatId: Long) {
        execute(clownStatService.sendHelp(chatId))
    }

    fun sendCurrentStat(chatId: Long) {
        execute(clownStatService.sendCurrentStat(chatId))
    }

}