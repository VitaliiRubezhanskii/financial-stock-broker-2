FROM adoptopenjdk/openjdk11
WORKDIR /
ADD build/libs/order-service.jar order-service.jar
#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
