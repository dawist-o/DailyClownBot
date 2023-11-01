package com.dawisto.dailyclownbot.bot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class BotConfig(
  @Value("\${bot.name}") val botName: String,
  @Value("\${bot.token}")val botToken: String,
)