FROM openjdk:12-jdk
WORKDIR /
ADD build/libs/feign-hystrix-client.jar feign-hystrix-client.jar
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
EXPOSE 8099
