# Service
FROM openjdk:8-jdk-alpine
MAINTAINER Venkat Pandeti
VOLUME /tmp
EXPOSE 8081
ARG JAR_FILE=target/calculationservice-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} calculationservice.jar
ENTRYPOINT ["java", "-jar","/calculationservice.jar"]