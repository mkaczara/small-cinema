FROM openjdk:11.0.8-jre-slim
EXPOSE 8080
ARG JAR_FILE
ADD ${JAR_FILE} snall-cinema.jar
ENTRYPOINT ["java", "-jar", "snall-cinema.jar"]