FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY gradlew /app/gradlew
COPY gradle /app/gradle
COPY build.gradle settings.gradle /app/

RUN chmod +x /app/gradlew
RUN /app/gradlew --no-daemon dependencies

COPY src /app/src

RUN /app/gradlew --no-daemon bootJar

FROM aquasec/trivy:0.50.2 AS trivy-scan

WORKDIR /scan

COPY --from=build /app/build/libs /scan/libs

RUN trivy fs --no-progress --exit-code 1 --severity HIGH,CRITICAL /scan

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
