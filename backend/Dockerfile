FROM eclipse-temurin:17.0.8.1_1-jdk-jammy

RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

WORKDIR /app
RUN mkdir -p /app/images-profile /app/files-immobile
COPY . .
RUN mvn -N io.takari:maven:wrapper
RUN chmod +x ./mvnw && ./mvnw clean install -DskipTests
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=prod", "target/minhacasa-0.0.1-SNAPSHOT.jar" ]