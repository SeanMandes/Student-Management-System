FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/Student-Management-System-reboot-main-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-cp", "app.jar", "main.java.Main"]