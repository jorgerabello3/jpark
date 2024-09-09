FROM gradle:8-jdk17-alpine AS temp_build_image

ENV APP_HOME=/usr/app

WORKDIR $APP_HOME

COPY build.gradle settings.gradle gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle
COPY . .

RUN ./gradlew clean build --no-daemon

FROM openjdk:17-jdk-slim

ENV ATIFACT_NAME=jpark-0.0.1-SNAPSHOT.jar

ENV APP_HOME=/usr/app

WORKDIR $APP_HOME

COPY --from=temp_build_image $APP_HOME/build/libs/jpark-0.0.1-SNAPSHOT.jar .

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "jpark-0.0.1-SNAPSHOT.jar"]