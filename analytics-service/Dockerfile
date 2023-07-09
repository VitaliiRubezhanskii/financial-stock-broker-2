FROM openjdk:17-oracle
WORKDIR /
ADD build/libs/analytics-service.jar analytics-service.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
