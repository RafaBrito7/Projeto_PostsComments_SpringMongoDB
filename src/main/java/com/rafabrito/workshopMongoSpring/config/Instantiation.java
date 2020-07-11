package com.rafabrito.workshopMongoSpring.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rafabrito.workshopMongoSpring.domain.Post;
import com.rafabrito.workshopMongoSpring.domain.User;
import com.rafabrito.workshopMongoSpring.dto.AuthorDTO;
import com.rafabrito.workshopMongoSpring.repository.PostRepository;
import com.rafabrito.workshopMongoSpring.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository; // Para manipularmos o BD
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll(); // Para apagar todos os dados do BD ao inicializar
		postRepository.deleteAll();
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com");
		User u2 = new User(null, "Alex Green", "alex@gmail.com");
		User u3 = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1,u2,u3)); // Teremos que salvar antes os Users, para que o ID não fique nulo na chamada do DTO
		
		Post p1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem","Vou viajar para São Paulo. Abraços!", new AuthorDTO(u1));
		Post p2 = new Post(null, sdf.parse("23/03/2018"),"Bom dia" ,"Acordei feliz hoje!", new AuthorDTO(u1));
		
		postRepository.saveAll(Arrays.asList(p1,p2));
		
	}

}
