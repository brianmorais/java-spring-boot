package com.brianmorais.libraryapi.repository;

import com.brianmorais.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 31));
        var autorSalvo = repository.save(autor);
        System.out.println(autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("d75e4b4a-9d17-4710-8f10-51127e257c13");
        Optional<Autor> autor = repository.findById(id);

        if (autor.isPresent()) {
            var encontrado = autor.get();
            System.out.println("Dados do autor: " + autor.toString());
            
            encontrado.setDataNascimento(LocalDate.of(1960, 1, 30));
            repository.save(encontrado);
        }
    }
}
