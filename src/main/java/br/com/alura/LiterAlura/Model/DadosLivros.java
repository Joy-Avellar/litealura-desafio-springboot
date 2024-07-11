package br.com.alura.LiterAlura.Model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(@JsonAlias("id") Integer idGutenberg,
                          @JsonAlias("title") String titulo,
                          @JsonAlias("authors") List<DadosAutores> autor,
                          @JsonAlias("languages") List<String> idiomas,
                          @JsonAlias("download_count")Integer downloads) {

}

