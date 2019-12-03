FROM openjdk:8-jdk-alpine
ADD target/config-0.0.1-SNAPSHOT.jar config-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/config-0.0.1-SNAPSHOT.jar"]
EXPOSE 8888
