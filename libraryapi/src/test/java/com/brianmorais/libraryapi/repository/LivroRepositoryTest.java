package com.brianmorais.libraryapi.repository;

import com.brianmorais.libraryapi.model.Autor;
import com.brianmorais.libraryapi.model.GeneroLivro;
import com.brianmorais.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {
    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("123123123");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("d714794a-4228-42d4-a596-fe67879be648"))
                .orElse(null);

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    public void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("123123123");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Carlos");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 31));

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    public void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("123123123");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Carlos");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 31));

        autorRepository.save(autor);

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutorLivro() {
        var livro = livroRepository
                .findById(UUID.fromString("a2a83454-8e91-4a00-ad2e-70ae07956529"))
                .orElse(null);

        var autor = autorRepository
                .findById(UUID.fromString("f235ce5c-f2db-4b1e-a3b2-09b88ff6b529"))
                .orElse(null);

        if (livro != null) {
            livro.setAutor(autor);
            livroRepository.save(livro);
        }
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("daed83b3-65fd-49eb-9400-cbc0af13059d");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());

//        System.out.println("Autor:");
//        System.out.println(livro.getAutor().getNome());
    }
}