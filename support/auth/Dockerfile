FROM openjdk:12-jdk
WORKDIR /
ADD target/auth-0.0.1-SNAPSHOT.jar auth-0.0.1-SNAPSHOT.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]