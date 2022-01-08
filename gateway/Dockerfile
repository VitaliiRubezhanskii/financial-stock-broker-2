FROM openjdk:12-jdk
ADD build/libs/gateway.jar gateway.jar
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
EXPOSE 5000