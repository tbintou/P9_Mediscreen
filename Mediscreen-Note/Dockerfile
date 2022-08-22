FROM maven:3-openjdk-11 AS build
MAINTAINER bintou01
COPY ./target/mediscreen-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]
