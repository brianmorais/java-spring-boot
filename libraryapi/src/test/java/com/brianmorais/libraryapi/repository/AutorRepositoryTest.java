package com.brianmorais.libraryapi.repository;

import com.brianmorais.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
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

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores" + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("d75e4b4a-9d17-4710-8f10-51127e257c13");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("d75e4b4a-9d17-4710-8f10-51127e257c13");
        repository.findById(id).ifPresent(autor -> repository.delete(autor));
    }
}
