package ntk.tlu.project1;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Project1Application {
	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}
	
	// mdel Mapper
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
