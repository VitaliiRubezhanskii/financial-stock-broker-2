FROM openjdk:12-jdk
WORKDIR /
ADD target/account-service-0.0.1-SNAPSHOT.jar account-service-0.0.1-SNAPSHOT.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
EXPOSE 8081