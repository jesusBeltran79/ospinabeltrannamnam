package co.edu.unbosque.ospinabeltrannamnam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OspinabeltrannamnamApplication {

	public static void main(String[] args) {
		SpringApplication.run(OspinabeltrannamnamApplication.class, args);
	}
	
	@SpringBootApplication
	public class nombreproyectoApplication {
		@Bean
		public ModelMapper getModelMapper() {
			return new  ModelMapper();
		}
	}

}
