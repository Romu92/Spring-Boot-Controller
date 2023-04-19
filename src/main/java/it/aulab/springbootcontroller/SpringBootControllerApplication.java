// nell'application posso creare i miei bean


package it.aulab.springbootcontroller;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// import it.aulab.springbootcontroller.util.mapper.CreatePostDTOToPostPropertyMap;

@SpringBootApplication
public class SpringBootControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootControllerApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		// CreatePostDTOToPostPropertyMap createPostDTOToPostMap = new CreatePostDTOToPostPropertyMap();
		// mapper.addMappings(createPostDTOToPostMap);
		return mapper;
		/*
		 *	Associazione a classi anonime alle expression function di Javascript
		 *
		 *	ESEMPIO in JAVASCRIPT
		 * 	let sum = function (a, b) {
		 * 		return a + b;
		 * 	}
		 * 	sum(1,2); -> 3
		 *
		 * ESEMPIO in JAVA
		 * PropertyMap<CreatePostDTO, Post> createPostDtoToPostMapping = new PropertyMap<CreatePostDTO, Post>() {
		 *  // source
		 *  // destination
		 * 	protected void configure() {
		 * 		map().setId(null);
		 * 		map().getAuthor().setId(source.getAuthorId());
		 * 	}
		 * };
		 * mapper.addMappings(createPostDtoToPostMapping);
		 * 
		 */

		//Provider di ModelMapper

		// Sto implementando la classe in it.aulab.springbootcontroller.util.mapper
	}

}
