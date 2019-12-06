FROM openjdk:12-jdk
WORKDIR /
ADD target/auth-0.0.1-SNAPSHOT.jar auth-0.0.1-SNAPSHOT.jar
ADD /setup.sh setup.sh
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
ENTRYPOINT ["/setup.sh"]
EXPOSE 8000