FROM openjdk:12-jdk
ADD build/libs/monitoring.jar monitoring.jar
ENTRYPOINT ["java","-jar","/monitoring.jar"]
EXPOSE 8761
