FROM openjdk:12-jdk

ADD build/libs/config.jar config.jar
VOLUME /var/lib/financial-stock-broker-2/configuration
ENTRYPOINT ["java","-jar","/config.jar"]
EXPOSE 8888


