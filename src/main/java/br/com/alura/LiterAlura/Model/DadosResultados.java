package br.com.alura.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosResultados {

    @JsonAlias("results") List<DadosLivros> livros;

    public List<DadosLivros> getLivros() {
        return livros;
    }

    public void setLivros(List<DadosLivros> livros) {
        this.livros = livros;
    }

}