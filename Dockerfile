#STAGE: BUILD
FROM docker.io/library/maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B package -DskipTests

#STAGE: RUN
FROM docker.io/library/eclipse-temurin:21-jre
WORKDIR /app
RUN useradd  -r -u 1001 appuser
COPY --from=build /app/target/*.jar app.jar
USER appuser
EXPOSE 8881
ENTRYPOINT ["java", "-jar", "app.jar"]