FROM openjdk:22-jdk-oracle AS build

WORKDIR /app

COPY . /app

COPY mvnw /app
COPY .mvn /app/.mvn

RUN chmod +x mvnw

RUN ./mvnw spring-boot:build-image

FROM openjdk:22-jdk-oracle

WORKDIR /app

COPY --from=build /app/target/*.jar /app/application.jar

ENTRYPOINT ["java", "-jar", "/app/application.jar"]

FROM openjdk:22-jdk-oracle

WORKDIR /app

COPY . .

ENTRYPOINT ["./mvnw", "spring-boot:run"]