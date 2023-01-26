##FROM maven:3-eclipse-temurin-17 AS builder
##FROM alpine:3.17.1 AS builder
#FROM maven:3.8.7-eclipse-temurin-17-alpine AS builder
##WORKDIR /letMeIn
#
#COPY pom.xml .
#COPY src ./src
#
#RUN apk add --no-cache npm
#
#
##EXPOSE 8080
##RUN apt-get install nodejs npm
#CMD ["mvn", "spring-boot:run"]
#


##FROM eclipse-temurin:17-alpine AS runtime
#FROM tomcat:jre17-temurin
##FROM eclipse-temurin:17-alpine
#
#
#WORKDIR /letMeIn
#
#USER 1000
#
#COPY --from=builder /letMeIn/target/letmein-?-?-?-SNAPSHOT.war /usr/local/tomcat/webapps/
#
#EXPOSE 8080
#
##CMD ["java", "-jar", "app.jar"]





FROM maven:3.8.7-eclipse-temurin-17-alpine AS build

# Copy pom.xml to the image
COPY pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml verify clean --fail-never

# Copy the source code
COPY src /home/app/src
RUN mvn -f /home/app/pom.xml package

# Install Tomcat    & openjdk 8 (openjdk has java and javac)
FROM tomcat:jdk17-openjdk
# Copy source files to tomcat folder structure
COPY --from=build /home/app/target/letmein-?-?-?-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080