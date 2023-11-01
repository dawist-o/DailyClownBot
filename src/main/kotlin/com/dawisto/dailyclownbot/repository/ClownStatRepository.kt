package com.dawisto.dailyclownbot.repository

import com.dawisto.dailyclownbot.entity.ClownStat
import org.springframework.data.repository.CrudRepository

interface ClownStatRepository : CrudRepository<ClownStat, Long>{


    fun findByChatIdAndUserName(chatId: Long, userName: String): ClownStat?


    fun findByChatId(chatId: Long): List<ClownStat>


}