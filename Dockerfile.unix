FROM eclipse-temurin:22 AS development

RUN apt-get update && apt-get -y upgrade
RUN apt-get install -y inotify-tools dos2unix

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY . .

CMD ["sh", "run.sh"]
