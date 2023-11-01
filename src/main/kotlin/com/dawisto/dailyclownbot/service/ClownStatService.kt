package com.dawisto.dailyclownbot.service

import com.dawisto.dailyclownbot.entity.ClownStat
import com.dawisto.dailyclownbot.messages.Message
import com.dawisto.dailyclownbot.repository.ClownStatRepository
import com.dawisto.dailyclownbot.repository.ClownWordRepository
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class ClownStatService(
    val clownStatRepository: ClownStatRepository,
    val clownWordRepository: ClownWordRepository
) {

    fun handleUpdate(update: Update, botUsername: String) {
        val replyMessage = update.message
        replyMessage.replyToMessage ?: return
        if(botUsername == replyMessage.replyToMessage.from.userName)
            return

        // not working
        val isClownReply = clownWordRepository.findAll().map { it.word }.any { replyMessage.text.contains(it) }
        if (isClownReply) {
            // не работает
            val clownStat =
                clownStatRepository.findByChatIdAndUserName(
                    replyMessage.chatId,
                    replyMessage.replyToMessage.from.userName
                ) ?: ClownStat(
                    replyMessage.chatId,
                    replyMessage.replyToMessage.from.userName,
                    0
                )
            clownStat.let {
                it.clownsCount++
                clownStatRepository.save(it)
            }

        }
    }

    fun sendHelp(chatId: Long) = SendMessage(
        chatId.toString(),
        Message.HELP.format(clownWordRepository.findAll().joinToString { it.word })
    )


    fun sendClownNotifications(): List<SendMessage> {
        val messages = clownStatRepository.findAll()
            .groupBy { it.chatId }
            .map { (key, value) ->
                val maxClownsCount = value.maxBy { it.clownsCount }
                SendMessage(
                    key.toString(),
                    Message.NOTIFICATION.format(maxClownsCount.userName, maxClownsCount.clownsCount)
                )
            }
        clownStatRepository.deleteAll()
        return messages
    }

    fun sendCurrentStat(chatId: Long): SendMessage {
        val statInChat = clownStatRepository.findByChatId(chatId)
        return if (statInChat.isEmpty())
            SendMessage(chatId.toString(), Message.EMPTY_STAT)
        else {
            val userStat = statInChat.sortedByDescending { it.clownsCount }
                .joinToString(separator = "\n", transform = { Message.USER_STAT.format(it.userName, it.clownsCount) })
            SendMessage(chatId.toString(), Message.STAT.plus(userStat))
        }
    }
}

