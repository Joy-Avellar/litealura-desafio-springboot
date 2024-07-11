package br.com.alura.LiterAlura.Service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
