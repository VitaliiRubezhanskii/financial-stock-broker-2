FROM openjdk:12-jdk
VOLUME /tmp
WORKDIR /
ADD /target/turbine-0.0.1-SNAPSHOT.jar turbine-0.0.1-SNAPSHOT.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]