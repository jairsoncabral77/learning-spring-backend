package br.org.meuvoto.runtime;

import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication(scanBasePackages={"br.org.meuvoto.controller",
			"br.org.meuvoto.service","br.org.meuvoto.repository"})
@ImportResource("beans.xml")
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
    	Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
    	b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    	return b;
    }
}