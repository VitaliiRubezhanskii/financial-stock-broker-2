FROM openjdk:12-jdk
WORKDIR /
ADD target/feign-hystrix-client-0.0.1-SNAPSHOT.jar feign-hystrix-client-0.0.1-SNAPSHOT.jar
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
EXPOSE 8099
