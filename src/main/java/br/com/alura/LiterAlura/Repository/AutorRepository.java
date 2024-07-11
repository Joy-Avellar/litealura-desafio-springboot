package br.com.alura.LiterAlura.Repository;

import br.com.alura.LiterAlura.Model.Autores;
import br.com.alura.LiterAlura.Model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autores, Long> {

    @Query("SELECT a FROM Autores a WHERE a.dataNascimento <= :ano AND (a.falecimento IS NULL OR a.falecimento >= :ano)")
    List<Autores> findAutoresVivosEmAno(@Param("ano") Integer ano);

}
