package com.dawisto.dailyclownbot.messages

object Message {

    const val CLOWN: String = "\uD83E\uDD21"
    const val EMPTY_STAT: String = "Странно, но в этом чате пока не было обнаружено клоунов\uD83E\uDD14"
    const val STAT: String = "Текущее состояние клоунов в чате:\n"
    const val USER_STAT: String = "Пользователь @%s имеет на своем счету %s $CLOWN"

    const val NOTIFICATION: String = "Клоуном недели становится @%s!\uD83C\uDF89" +
            "\nКоличество полученных ${CLOWN}: %s"

    const val HELP: String = "Привет я занимаюсь подсчетом клоунов!${CLOWN}" +
            "\nДля того, чтобы кто-то стал клоуном необходимо ответить на сообщение с использованием ключевых слов: %s"
}