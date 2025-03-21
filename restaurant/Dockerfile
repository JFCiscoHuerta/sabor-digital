FROM openjdk:21-jdk AS builder
WORKDIR /app/restaurant
COPY ./pom.xml /app
COPY ./restaurant/.mvn ./.mvn
COPY ./restaurant/mvnw .
COPY ./restaurant/pom.xml .
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./restaurant/src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jdk
WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/restaurant/target/restaurant-1.0-SNAPSHOT.jar .
ARG PORT_APP=8082
ENV PORT=$PORT_APP
EXPOSE $PORT
ENTRYPOINT ["java","-jar","./restaurant-1.0-SNAPSHOT.jar"]
