FROM openjdk:21-jdk AS builder
WORKDIR /app/waiter
COPY ./pom.xml /app
COPY ./waiter/.mvn ./.mvn
COPY ./waiter/mvnw .
COPY ./waiter/pom.xml .
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./waiter/src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jdk
WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/waiter/target/waiter-1.0-SNAPSHOT.jar .
ARG PORT_APP=8084
ENV PORT=$PORT_APP
EXPOSE $PORT
ENTRYPOINT ["java","-jar","./waiter-1.0-SNAPSHOT.jar"]