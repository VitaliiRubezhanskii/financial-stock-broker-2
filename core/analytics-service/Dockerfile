FROM openjdk:8
WORKDIR /
ADD target/analytics-service-0.0.1-SNAPSHOT.jar analytics-service-0.0.1-SNAPSHOT.jar
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
