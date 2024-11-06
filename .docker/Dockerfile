FROM maven:3.8.8-eclipse-temurin-17-alpine

WORKDIR /app

COPY ./pom.xml ./

RUN mvn dependency:go-offline -B

COPY ./src ./src
RUN mvn package -DskipTests

EXPOSE 8080

CMD [ "java", "-jar", "target/consol-crud-java-0.0.1-SNAPSHOT.jar"]

