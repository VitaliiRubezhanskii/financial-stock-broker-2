FROM openjdk:12-jdk

ADD target/config-0.0.1-SNAPSHOT.jar config-0.0.1-SNAPSHOT.jar
VOLUME /var/lib/financial-stock-broker-2/configuration
ENV SPRING_PROFILES_ACTIVE docker
ENV PORT 8888
ENTRYPOINT ["java","-jar","/config-0.0.1-SNAPSHOT.jar"]
EXPOSE 8888


