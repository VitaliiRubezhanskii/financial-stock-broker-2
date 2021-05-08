FROM openjdk:12-jdk
ADD target/monitoring-0.0.1-SNAPSHOT.jar monitoring-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/monitoring-0.0.1-SNAPSHOT.jar"]
ENV CONFIGSERVER_URI http://configserver:8888
ENV PORT 8761
EXPOSE 8761
