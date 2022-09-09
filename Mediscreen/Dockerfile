FROM maven:3-openjdk-11 AS build
WORKDIR /Mediscreen
COPY . .
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
RUN mvn clean
CMD mvn spring-boot:run