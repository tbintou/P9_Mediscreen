FROM maven:3-openjdk-11 AS build
MAINTAINER mediscreen-app:tagname
COPY ./target/Mediscreen-Front-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]