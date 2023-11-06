package com.dawisto.dailyclownbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import org.springframework.core.env.PropertySource


@SpringBootApplication
class DailyClownBotApplication {

    @EventListener
    fun handleContextRefreshed(event: ContextRefreshedEvent) {
        val env = event.applicationContext.environment as ConfigurableEnvironment
        env.propertySources
            .stream()
            .filter { ps: PropertySource<*>? -> ps is MapPropertySource }
            .map<Set<String>> { ps: PropertySource<*> -> (ps as MapPropertySource).source.keys }
            .flatMap<String> { obj: Set<String> -> obj.stream() }
            .distinct()
            .sorted()
            .forEach { key: String? ->
                println(
                    String.format(
                        "%s=%s", key, env.getProperty(
                            key!!
                        )
                    )
                )
            }
    }
}

fun main(args: Array<String>) {
    runApplication<DailyClownBotApplication>(*args)
}