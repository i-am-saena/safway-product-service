# Mention docker base image for java on which our application will run
FROM openjdk:17-jdk-slim

# Inside docker container create a folder/directory called app
WORKDIR /app


# once our app gets build, it creates a jar file inside target folder, this command will copy that jar file from target folder of our app to the folder we have created in docker
COPY target/safeway-product-application-0.0.1.jar app.jar


# Inside docker container app will run on port 8080
EXPOSE 8080


# This command will run the jar file we have copied frrom target folder to docker container
ENTRYPOINT ["java", "-jar", "app.jar"]