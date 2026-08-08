# Build the application
FROM maven:3.9.16-eclipse-temurin-25-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src src

RUN ./mvnw clean package -DskipTests

# Create a custom Java runtime
FROM eclipse-temurin:25-jdk-alpine AS jre-build

COPY --from=build /app/target/*.jar /app.jar

RUN jdeps \
    --ignore-missing-deps \
    --recursive \
    --multi-release 25 \
    --print-module-deps /app.jar > /modules.txt

RUN $JAVA_HOME/bin/jlink \
    --add-modules $(cat /modules.txt) \
    --strip-debug \
    --compress=2 \
    --no-header-files \
    --no-man-pages \
    --output /javaruntime

# Create runtime image
FROM alpine:3.24.1

ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="$JAVA_HOME/bin:$PATH"

COPY --from=jre-build /javaruntime $JAVA_HOME

WORKDIR /app

COPY --from=build /app/target/*.jar /app.jar

RUN addgroup -S spring && adduser -S spring -G spring

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]