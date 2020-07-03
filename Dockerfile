FROM maven:3.6.3-openjdk-11 AS MAVEN-BUILD
WORKDIR /build
COPY . /build/
RUN mvn dependency:go-offline
RUN mvn package

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=MAVEN-BUILD /build/web/target/web-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
CMD java -jar web-0.0.1-SNAPSHOT.jar
