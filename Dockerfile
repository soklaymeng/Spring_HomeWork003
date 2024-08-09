# BUILD STAGE
FROM maven:3.8.7-eclipse-temurin-19 AS build
# Set the working directory inside the container
WORKDIR /app

# Copy current local directory to /app which current directory in container
COPY . .

# Clean the existing build and package the application to create JAR file
RUN mvn clean package

# RUN STAGE

# Specify base image for final stage for running JAVA application
FROM eclipse-temurin:17.0.8_7-jre-alpine

# Copy the executable JAR file from build stage to /app directory in container and rename it to app.jar
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]