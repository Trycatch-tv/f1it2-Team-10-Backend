FROM maven:3.9.2-eclipse-temurin-17-focal AS build
COPY /citasync/src /home/app/src
COPY /citasync/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY --from=build /home/app/target/citasync*.jar /usr/local/lib/app.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]