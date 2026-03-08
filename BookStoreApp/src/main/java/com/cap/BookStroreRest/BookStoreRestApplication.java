package com.cap.BookStroreRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@AutoConfiguraion, @ComponentScan, @Configuration, @Documented
@EnableCaching
public class BookStoreRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreRestApplication.class, args);
	}

}
