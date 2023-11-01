package com.dawisto.dailyclownbot.sheduler

import com.dawisto.dailyclownbot.entity.ClownStat
import com.dawisto.dailyclownbot.repository.ClownStatRepository
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class Notification(
    val clownStatRepository: ClownStatRepository
) {

    @Scheduled(cron = "0 * * * * *")
    fun sendDailyClown(): String {
        val allStat = clownStatRepository.findAll()
        val chatClowns = allStat.groupBy { it.chatId }
        chatClowns.forEach { chatId, stat -> }
        return "Max"
    }

    private fun sendMessage(clownStat: ClownStat, chatId: Long) {


    }

}