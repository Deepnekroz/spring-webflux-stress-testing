# Dockerfile References: https://docs.docker.com/engine/reference/builder/

# Start from golang v1.11 base image
FROM openjdk:8-jre-alpine

# Set the Current Working Directory inside the container
WORKDIR /srv/javaperf

# Copy everything from the current directory to the PWD(Present Working Directory) inside the container
COPY ./target/stresstest-0.0.1-SNAPSHOT.jar .

# This container exposes port 8080 to the outside world
EXPOSE 8080
EXPOSE 9090

# Run the executable
ENTRYPOINT (sleep 30 && java -jar stresstest-0.0.1-SNAPSHOT.jar ...)