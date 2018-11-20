FROM maven:3-jdk-8-alpine as builder

ARG MAVEN_OPTS

ENV MAVEN_OPTS=${MAVEN_OPTS}

RUN mkdir build

COPY ./ build/

RUN cd build \
	&& mvn install

FROM  openjdk:8-jre-alpine

WORKDIR /opt/parlamento

COPY --from=builder ./build/target/parlamento-microservice-*.jar /opt/parlamento/parlamento-microservice.jar

EXPOSE 8080

# RUN COMMAND: "docker run --env JDBC_URL=<url> --env DB_USERNAME=<user> --env DB_PASSWORD=<password> -p 8080:8080 <tag>

# é preciso fornecer as variáveis de ambiente: 	DB_URL, DB_USERNAME e DB_PASSWORD
ENTRYPOINT ["java","-jar","parlamento-microservice.jar"]