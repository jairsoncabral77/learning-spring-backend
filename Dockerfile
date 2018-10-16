FROM java:8u111-jdk

ENV RUNNABLE_JAR=/opt/parlamento/parlamento-microservice.jar

WORKDIR /opt/parlamento

ADD ./target/parlamento-microservice-*.jar /opt/parlamento/parlamento-microservice.jar

EXPOSE 8080

# RUN COMMAND: "docker run --env JDBC_URL=<url> --env DB_USERNAME=<user> --env DB_PASSWORD=<password> -p 8080:8080 <tag>

# é preciso fornecer as variáveis de ambiente: 	DB_URL, DB_USERNAME e DB_PASSWORD
ENTRYPOINT ["java","-jar","parlamento-microservice.jar"]