FROM adoptopenjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN apt-get install curl
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=prod"]