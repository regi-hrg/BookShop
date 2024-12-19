package hu.unideb.inf.BookShop;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper(){
		ModelMapper m = new ModelMapper();
		return m;

		//return new ModelMapper();
	}

}
