FROM openjdk:21-jdk AS builder
WORKDIR /app/table
COPY ./pom.xml /app
COPY ./table/.mvn ./.mvn
COPY ./table/mvnw .
COPY ./table/pom.xml .
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./table/src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jdk
WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/table/target/table-1.0-SNAPSHOT.jar .
ARG PORT_APP=8083
ENV PORT=$PORT_APP
EXPOSE $PORT
ENTRYPOINT ["java","-jar","./table-1.0-SNAPSHOT.jar"]