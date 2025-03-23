FROM openjdk:21-jdk AS builder
WORKDIR /app/order
COPY ./pom.xml /app
COPY ./order/.mvn ./.mvn
COPY ./order/mvnw .
COPY ./order/pom.xml .
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./order/src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jdk
WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/order/target/order-1.0-SNAPSHOT.jar .
ARG PORT_APP=8081
ENV PORT=$PORT_APP
EXPOSE $PORT
ENTRYPOINT ["java","-jar","./order-1.0-SNAPSHOT.jar"]


