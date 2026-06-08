# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre
WORKDIR /app
# Assumes your build generates a jar named 'app.jar' or matches this pattern
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Java app listens on (Render defaults to 10000)
EXPOSE 10000

# Run the jar and pass the port to your app via environment variable if needed
CMD ["java", "-jar", "app.jar"]