# Build
FROM openjdk:22-jdk-oracle AS build

WORKDIR /app

COPY . /app

COPY mvnw /app
COPY .mvn /app/.mvn

RUN chmod +x mvnw

RUN ./mvnw spring-boot:build-image


# Prod
FROM openjdk:22-jdk-oracle AS production

WORKDIR /app

COPY --from=build /app/target/*.jar /app/application.jar

ENTRYPOINT ["java", "-jar", "/app/application.jar"]

# Dev
FROM openjdk:22-jdk-oracle AS development

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
