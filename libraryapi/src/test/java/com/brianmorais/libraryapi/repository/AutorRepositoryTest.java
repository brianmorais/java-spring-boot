package com.brianmorais.libraryapi.repository;

import com.brianmorais.libraryapi.model.Autor;
import com.brianmorais.libraryapi.model.GeneroLivro;
import com.brianmorais.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

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

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americana");
        autor.setDataNascimento(LocalDate.of(1970, 8, 5));

        Livro livro = new Livro();
        livro.setIsbn("20847-84874");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("O roubo da casa assombrada");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("99999-84874");
        livro2.setPreco(BigDecimal.valueOf(650));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("Outro livro");
        livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

//        livroRepository.saveAll(autor.getLivros());
    }
}
