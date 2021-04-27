FROM openjdk:12-jdk
WORKDIR /
ADD target/quotes-provider-service-0.0.1-SNAPSHOT.jar quotes-provider-service-0.0.1-SNAPSHOT.jar
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]