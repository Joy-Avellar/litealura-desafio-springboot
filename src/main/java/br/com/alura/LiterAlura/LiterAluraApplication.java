package br.com.alura.LiterAlura;

import br.com.alura.LiterAlura.Principal.Principal;
import br.com.alura.LiterAlura.Repository.AutorRepository;
import br.com.alura.LiterAlura.Repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LivrosRepository repositorio;
	@Autowired
	private AutorRepository repositorioAutor;


	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio, repositorioAutor);
		principal.exibeMenu();
	}
}
