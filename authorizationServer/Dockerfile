FROM openjdk:17

WORKDIR /app
COPY ./target/authorizationServer-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080
EXPOSE 8081

CMD ["java", "-jar", "authorizationServer-0.0.1-SNAPSHOT.jar"]