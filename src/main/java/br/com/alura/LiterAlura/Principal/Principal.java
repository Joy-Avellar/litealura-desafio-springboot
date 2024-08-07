package br.com.alura.LiterAlura.Principal;

import br.com.alura.LiterAlura.Model.Autores;
import br.com.alura.LiterAlura.Model.DadosLivros;
import br.com.alura.LiterAlura.Model.DadosResultados;
import br.com.alura.LiterAlura.Model.Livros;
import br.com.alura.LiterAlura.Repository.AutorRepository;
import br.com.alura.LiterAlura.Repository.LivrosRepository;
import br.com.alura.LiterAlura.Service.ConsumoApi;
import br.com.alura.LiterAlura.Service.ConverteDados;
import org.aspectj.apache.bcel.Repository;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {


    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private List<DadosLivros> dadosLivros = new ArrayList<>();
    private List<Autores> autores = new ArrayList<>();
    private List<Livros> livros = new ArrayList<>();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private LivrosRepository repositorio;
    private AutorRepository repositorioAutor;



    public Principal(LivrosRepository repositorio, AutorRepository repositorioAutor) {
        this.repositorio = repositorio;
        this.repositorioAutor = repositorioAutor;
    }

    public void exibeMenu() {

        var opcao = -1;

        while (opcao != 0) {
            try {

                var menu = """
                        
                        88           88                                               88                                      
                        88           ""    ,d                                         88                                      \s
                        88                 88                                         88                                      \s
                        88           88  MM88MMM   ,adPPYba,  8b,dPPYba,  ,adPPYYba,  88  88       88  8b,dPPYba,  ,adPPYYba, \s
                        88           88    88     a8P_____88  88P'   "Y8  ""     `Y8  88  88       88  88P'   "Y8  ""     `Y8 \s
                        88           88    88     8PP""\"""\""  88          ,adPPPPP88  88  88       88  88          ,adPPPPP88 \s
                        88           88    88,    "8b,   ,aa  88          88,    ,88  88  "8a,   ,a88  88          88,    ,88 \s
                        88888888888  88    "Y888   `"Ybbd8"'  88          `"8bbdP"Y8  88   `"YbbdP'Y8  88          `"8bbdP"Y8 \s
                                                                                                                              \s
                        ｡☆✼★━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★✼☆｡
                               Bem - Vindo! O que deseja fazer?
                                                
                                1 - Buscar livro pelo título
                                2 - Listar livros registrados
                                3 - Lista nossos autores
                                4 - Listar autores em determinado ano
                                5 - Listar livros em determinado idioma
                                0 - Sair
                         ｡☆✼★━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★✼☆｡
                        """;
                System.out.println(menu);
                System.out.println("Digite uma opção: ");
                opcao = leitura.nextInt();
                leitura.nextLine();


                switch (opcao) {

                    case 1:
                        buscarLivro();
                        break;
                    case 2:
                        listarLivros();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        listarAutorPorAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("""
                                 ∧,,,∧
                                (=•·•=)
                                /   づ♡ Obrigada por Usar
                                """);
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Opção invalida, tente novamente");
                leitura.nextLine();
            }
        }

    }

    private Livros getDadosLivros() {
        System.out.println("Digite o nome de um livro. Certifique-se de que está com o titulo original");
        var escolhaUsuario = leitura.nextLine();

        String json = consumo.obterDados(ENDERECO + escolhaUsuario.replace(" ", "%20"));
        DadosResultados dados = conversor.obterDados(json, DadosResultados.class);
        if (dados != null && dados.getLivros() != null && !dados.getLivros().isEmpty()){
            DadosLivros livro = dados.getLivros().get(0);
            return new Livros(livro); } else {
                System.out.println("Livro não encontrado");
                return null;
        }
    }

    private void buscarLivro() {
        try{
            Livros dados = getDadosLivros();
            List<Livros> existingLivro = repositorio.findByTitulo(dados.getTitulo());
            if (!existingLivro.isEmpty()) {
                System.out.println("O livro já está no banco de dadps: " + existingLivro.get(0));
             } else {
                repositorio.save(dados);
                System.out.println(dados);
            } } catch (NullPointerException ex) {
                System.out.println("Erro ao salvar livro. Tente novamente, " +
                        "certifique-se de que o titulo do livro está no idioma original");
        }
    }

    private void listarLivros() {
        livros = repositorio.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livros::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutores() {
        autores = repositorioAutor.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autores::getNome))
                .forEach(System.out::println);
    }

    private void listarAutorPorAno() {
        System.out.println("Digite um ano");
        var ano = leitura.nextInt();
        leitura.nextLine();
        List<Autores> autoresVivos = repositorioAutor.findAutoresVivosEmAno(ano);
        if (!autoresVivos.isEmpty()){
        autoresVivos.stream()
                .sorted(Comparator.comparing(Autores::getDataNascimento))
                .forEach(System.out::println); } else {
            System.out.println("Nenhum Autor encontrado com a data requerida.");
        }

            }
            
    private void listarLivrosPorIdioma() {

        System.out.println("""
                        en - Ingles
                        fr - Frances
                        pt - Portugues
                        es - Espanhol
                                            
                        Escolha uma opção
                        """);

        var idiomaEscolhido = leitura.nextLine();
        List<Livros> livrosIdiomas = repositorio.findByIdiomasContainingIgnoreCase(idiomaEscolhido);
        System.out.println("Número de livros encontrados: " + livrosIdiomas.size());
        if (!livrosIdiomas.isEmpty()) {
        livrosIdiomas.stream()
                .sorted(Comparator.comparing(Livros::getTitulo))
                .forEach(System.out::println);} else {
            System.out.println("Nenhum livro encontrado com esse idioma. Revise a pesquisa.");
        }
    }

}
