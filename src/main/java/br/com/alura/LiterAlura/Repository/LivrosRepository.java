package br.com.alura.LiterAlura.Repository;

import br.com.alura.LiterAlura.Model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivrosRepository extends JpaRepository<Livros, Long> {
    List<Livros> findByTitulo(String titulo);

    List<Livros> findByIdiomasContains(String idioma);
}
