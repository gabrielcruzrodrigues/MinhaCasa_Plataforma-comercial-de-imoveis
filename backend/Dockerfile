FROM eclipse-temurin:17.0.8.1_1-jdk-jammy
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw && ./mvnw clean install -DskipTests
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=prod", "target/minhacasa-0.0.1-SNAPSHOT.jar" ]