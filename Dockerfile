FROM openjdk:11-jdk
WORKDIR /root
COPY . .
ARG JAR_FILE=root/k8s-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/k8s-0.0.1-SNAPSHOT.jar"]
