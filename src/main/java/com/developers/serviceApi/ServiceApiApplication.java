package com.developers.serviceApi;

import com.developers.serviceApi.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ServiceApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApiApplication.class, args);
	}

	@Autowired
	private SystemUserService systemUserService;
	@Override
	public void run(String... args) throws Exception {
		systemUserService.initializeUser();
	}
}
