package com.aluracursos.desafio;

import com.aluracursos.desafio.principal.Principal;
import com.aluracursos.desafio.repository.LibroRepository;
import com.aluracursos.desafio.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {
	@Autowired
	private LibroService libroService;

	@Autowired
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroService,repository);
		principal.muestraMenu();
	}
}
