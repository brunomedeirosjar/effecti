package com.bids.effecti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EffectiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EffectiApplication.class, args);
	}

}
