package com.brianmorais.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
//		var repository = context.getBean(AutorRepository.class);
//
//		exemploSalvarRegistro(repository);
	}

//	public static void exemploSalvarRegistro(AutorRepository autorRepository) {
//		Autor autor = new Autor();
//		autor.setNome("José");
//		autor.setNacionalidade("Brasileira");
//		autor.setDataNascimento(LocalDate.of(1950, 1, 31));
//		var autorSalvo = autorRepository.save(autor);
//		System.out.println(autorSalvo.toString());
//	}
}
