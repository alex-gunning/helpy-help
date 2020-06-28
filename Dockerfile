FROM maven:3.6.3-openjdk-11 AS MAVEN-BUILD
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /build/src/
RUN mvn package

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=MAVEN-BUILD /build/target/help-customer-service-0.0.1-SNAPSHOT.jar /app/
EXPOSE 3000
ENTRYPOINT java -jar help-customer-service-0.0.1-SNAPSHOT.jar