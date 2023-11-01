package com.dawisto.dailyclownbot.entity

import jakarta.persistence.*

@Entity
@Table(name = "clown_stat")
@IdClass(ClownStatId::class)
class ClownStat(
    @Id
    @Column(name = "chat_id")
    val chatId: Long,
    @Id
    @Column(name = "user_name")
    val userName: String,
    @Column(name = "clowns_count")
    var clownsCount: Int
)
