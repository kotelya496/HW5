package org.example.HW5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class Hw5Application {

	public static void main(String[] args) {
		SpringApplication.run(Hw5Application.class, args);
	}

}
