FROM maven:3-openjdk-11 AS build
MAINTAINER mediscreen-app:tagname
COPY ./target/Mediscreen-Rapport-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]
