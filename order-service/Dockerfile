
FROM openjdk:12-jdk
COPY . .
RUN ./mvnw clean package -DskipTests=true
ENTRYPOINT ["java","-jar","target/trading-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081