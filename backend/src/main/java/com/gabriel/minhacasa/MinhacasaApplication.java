package com.gabriel.minhacasa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinhacasaApplication implements CommandLineRunner {

	@Value("${info}")
	private String profile;

	public static void main(String[] args) {
		SpringApplication.run(MinhacasaApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("===========================");
		System.out.println(profile);
		System.out.println("===========================");
	}
}
 