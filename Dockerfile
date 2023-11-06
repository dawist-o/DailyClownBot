# это база, основа для имаджа
FROM eclipse-temurin:17-jdk-jammy

# это директория на базе, в которую будет класться билд
WORKDIR /bot

# ПЕРВЫЙ подход простой: загружаем все файл и билдим их градлом
# мы загружаем из текущей директории все файлы = ., в директорию имаджа с абсолютным путем /bot/.
COPY . .

# запускаем градл билд, запускается единожды при билде имаджа
RUN gradle clean build -x test

# Говорим докеру какие команды нужно запустить в нашем имадже, когда имадж стартанет, какие команды нужно выполнять при старте контейнера
CMD ["./gradlew", ":bootRun"]

# ВТОРОЙ подход сложнее: загружаем(COPY) градл файл, вызываем RUN ресолва депенденси градла, он ресолвит
# зависимости и создаем соурс файлы, мы загружаем только их в имадж (COPE src ./src)