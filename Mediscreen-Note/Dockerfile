FROM maven:3-openjdk-11 AS build
EXPOSE 8082
MAINTAINER bintou01
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
