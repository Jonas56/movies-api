FROM openjdk:11

WORKDIR /usr/src/movies

EXPOSE 8081

COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]