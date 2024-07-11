package br.com.alura.LiterAlura.Model;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String titulo;


    @ElementCollection
    @CollectionTable(name = "idiomas", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;
    private Integer downloads;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autores autor;

    public Livros(DadosLivros livros) {
        this.titulo = livros.titulo();
        this.downloads = livros.downloads();
        this.idiomas = livros.idiomas();
        Autores autores = new Autores(livros.autor().get(0));
        this.autor = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Livros() {
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }


    @Override
    public String toString() {
        return  "LIVRO " + id +
                "\n titulo='" + titulo + '\'' +
                "\n idiomas=" + idiomas +
                "\n downloads=" + downloads +
                "\n autor=" + autor;
    }
}
