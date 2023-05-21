package nl.novi.kapsalon;

import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.models.Treatment;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KapsalonApplication {

	public static void main(String[] args) {
		SpringApplication.run(KapsalonApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
