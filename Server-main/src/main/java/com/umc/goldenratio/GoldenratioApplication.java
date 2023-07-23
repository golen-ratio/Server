package com.umc.goldenratio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GoldenratioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoldenratioApplication.class, args);
	}

}
