FROM openjdk:17-oracle
WORKDIR /
ADD build/libs/quotes-provider-service.jar quotes-provider-service.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]