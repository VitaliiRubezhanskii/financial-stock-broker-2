FROM openjdk:12-jdk
WORKDIR /
ADD target/feign-hystrix-client-0.0.1-SNAPSHOT.jar feign-hystrix-client-0.0.1-SNAPSHOT.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
EXPOSE 8099
