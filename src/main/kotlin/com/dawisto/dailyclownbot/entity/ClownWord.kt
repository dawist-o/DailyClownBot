package com.dawisto.dailyclownbot.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "clown_words")
class ClownWord(
    @Id val word: String,
)