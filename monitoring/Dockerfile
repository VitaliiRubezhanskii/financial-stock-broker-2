FROM openjdk:12-jdk
WORKDIR /
ADD build/libs/monitoring.jar monitoring.jar
ENV SPRING_PROFILES_ACTIVE docker
ENV PORT 8761
ENV CONFIGSERVER_URI http://configserver:8888
#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
