package br.com.alura.LiterAlura.Repository;

import br.com.alura.LiterAlura.Model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivrosRepository extends JpaRepository<Livros, Long> {
    List<Livros> findByTitulo(String titulo);

//    @Query("SELECT l FROM Livros l WHERE :idioma MEMBER OF l.idiomas")
    List<Livros> findByIdiomasContainingIgnoreCase(String idioma);
}
