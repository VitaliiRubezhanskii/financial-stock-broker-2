FROM openjdk:17-oracle
WORKDIR /
ADD build/libs/order-service.jar order-service.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
