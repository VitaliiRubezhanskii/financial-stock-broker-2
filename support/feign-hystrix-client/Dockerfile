FROM openjdk:12-jdk
ADD target/feign-hystrix-client-0.0.1-SNAPSHOT.jar feign-hystrix-client-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/feign-hystrix-client-0.0.1-SNAPSHOT.jar"]
EXPOSE 8099