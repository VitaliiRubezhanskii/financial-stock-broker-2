FROM openjdk:12-jdk
VOLUME /tmp
WORKDIR /
ADD /zipkin.jar zipkin.jar
ADD /setup.sh setup.sh
ADD target/tracing-0.0.1-SNAPSHOT.jar tracing-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["/setup.sh"]


