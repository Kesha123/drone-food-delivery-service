FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /usr/app
COPY pom.xml .
COPY src ./src
COPY .mvn ./.mvn
COPY mvnw ./mvnw
COPY mvnw.cmd ./mvnw.cmd
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /usr/app
COPY --from=build /usr/app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "/usr/app/app.jar"]
