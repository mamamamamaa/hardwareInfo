package com.hardwareInfo.hardwareInfo;

import com.hardwareInfo.hardwareInfo.models.RegisterRequest;
import com.hardwareInfo.hardwareInfo.services.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hardwareInfo.hardwareInfo.entities.enums.Role.ADMIN;
import static com.hardwareInfo.hardwareInfo.entities.enums.Role.USER;

@SpringBootApplication
@RestController
public class HardwareInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardwareInfoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service) {
		return args -> {
			var user = RegisterRequest.builder()
					.username("user1")
					.email("user1@mail.com")
					.password("password")
					.role(USER)
					.build();
			System.out.println("User token: " + service.register(user).getToken());

			var admin = RegisterRequest.builder()
					.username("admin1")
					.email("admin1@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getToken());
		};
	}

	@GetMapping
	public String helloBebra() {
		return "Hello bebra";
	}

}
