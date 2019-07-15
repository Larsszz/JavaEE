package ua.kiev.prog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringGeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGeoApplication.class, args);
	}
}
