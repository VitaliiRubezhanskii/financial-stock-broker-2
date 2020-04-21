FROM openjdk:8
WORKDIR /
ADD target/analytics-service-0.0.1-SNAPSHOT.jar analytics-service-0.0.1-SNAPSHOT.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
