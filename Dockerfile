FROM maven:3.6.3-jdk-8 as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package 

FROM adoptopenjdk/openjdk8

COPY --from=builder /app/target/Car-Fleet-Management-System-0.0.1-SNAPSHOT.jar /java.jar

# Run the web service on container startup.
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=8080","-jar","/java.jar"]