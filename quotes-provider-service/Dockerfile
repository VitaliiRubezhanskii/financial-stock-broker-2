FROM openjdk:12-jdk
WORKDIR /
ADD build/libs/quotes-provider-service.jar quotes-provider-service.jar
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
#ENV JAVA_OPTS '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5505'
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]