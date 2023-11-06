# это база, основа для градла
FROM gradle AS app-build

WORKDIR /bot/build

COPY . .

RUN gradle --version
# запускаем градл билд, запускается единожды при билде имаджа
RUN gradle clean build -x test

# это база, основа для имаджа
FROM eclipse-temurin:17-jdk-jammy

# это директория на базе, в которую будет класться билд
WORKDIR /bot/runtime

COPY --from=app-build /bot/build/src/main/resources/application.* ./config/
COPY --from=app-build /bot/build/build/libs/DailyClownBot-0.0.1.jar ./DailyClownBot-0.0.1.jar

CMD [ "java", "-jar", "DailyClownBot-0.0.1.jar", "--spring.config.location=/bot/runtime/config/" ]

