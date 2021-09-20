FROM openjdk:8-jre-alpine
WORKDIR /usr/app
COPY target/*.jar ./app.jar
EXPOSE 8080
ENV PROFILES="docker"
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=${PROFILES}"]



