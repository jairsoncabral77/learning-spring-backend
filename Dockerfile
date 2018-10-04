FROM maven:3.5.0-jdk-8-alpine

COPY src test resource test_resource pom.xml /opt/parlamento/

WORKDIR /opt/parlamento

RUN mvn install

EXPOSE 8080
	
# é preciso fornecer as variáveis de ambiente: 	DB_URL, DB_USERNAME e DB_PASSWORD
	
ENTRYPOINT ["mvn","spring-boot:run","-P","prod"]