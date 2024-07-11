package br.com.alura.LiterAlura.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer dataNascimento;
    private Integer falecimento;


    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livros> livros = new ArrayList<>();

    public Autores() {}
    public Autores(DadosAutores dadosAUtor) {
        this.nome = dadosAUtor.nome();
        this.dataNascimento = dadosAUtor.dataNascimento();
        this.falecimento = dadosAUtor.dataFalecimento();
    }

    public List<Livros> getLivros() {
        return livros;
    }

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getFalecimento() {
        return falecimento;
    }

    public void setFalecimento(Integer falecimento) {
        this.falecimento = falecimento;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  nome + "( ☆ " + dataNascimento +   " - ✞ " + falecimento + ")";
    }

}
