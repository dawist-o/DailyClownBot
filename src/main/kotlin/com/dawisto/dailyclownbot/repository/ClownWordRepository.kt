package com.dawisto.dailyclownbot.repository

import com.dawisto.dailyclownbot.entity.ClownWord
import org.springframework.data.repository.CrudRepository

interface ClownWordRepository : CrudRepository<ClownWord, String>{
}