FROM adoptopenjdk/openjdk16
WORKDIR /
ADD build/libs/analytics-service.jar analytics-service.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
