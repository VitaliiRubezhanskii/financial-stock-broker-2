FROM openjdk:12-jdk
WORKDIR /
ADD target/order-service-0.0.1-SNAPSHOT.jar order-service-0.0.1-SNAPSHOT.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
