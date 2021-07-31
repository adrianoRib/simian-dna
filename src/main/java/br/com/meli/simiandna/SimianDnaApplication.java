package br.com.meli.simiandna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class SimianDnaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimianDnaApplication.class, args);
	}

}
