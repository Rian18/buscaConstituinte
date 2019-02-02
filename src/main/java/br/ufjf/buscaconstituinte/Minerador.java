package br.ufjf.buscaconstituinte;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Repositorio;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;
import persistence.RepositorioDAO;

/**
 *
 * @author Rian Alves
 */
public class Minerador {

    public List<Repositorio> Operacao(String constituinte) throws IOException, ClassNotFoundException, SQLException {

        Conexao conexao = new Conexao();
        String url = "";
        GitHub github = conexao.getConexao();
        GHRepositorySearchBuilder repo = github.searchRepositories();
        GHRepositorySearchBuilder repos = repo.q(constituinte);
        PagedIterable<GHRepository> repositorios = repos.list();
        List<Repositorio> lstRepositorios = new ArrayList<>();

        for (GHRepository repositorio : repositorios) {

            url = repositorio.getHtmlUrl().toString();
            GHUser user = repositorio.getOwner();
            System.out.println(repositorio.getId());
            System.out.println("Descrição do Repositório : " + repositorio.getDescription());
            System.out.println("Nome Completo : " + repositorio.getFullName());
            System.out.println("Linguagem de Programação : " + repositorio.getLanguage());
            System.out.println("URL: " + url);
            System.out.println("" + repositorio.getForks());
            System.out.println(""+ repositorio.getSize());
            System.out.println("" + repositorio.getStargazersCount());
            System.out.println("" + repositorio.getSubscribersCount());
            System.out.println("" + repositorio.getWatchers());
            System.out.println("-----------------||----------------");
            lstRepositorios.add(new Repositorio(repositorio.getId(),repositorio.getFullName(),repositorio.getName(),
                    url, repositorio.getDescription(), repositorio.getLanguage(), repositorio.getForks(),
                    repositorio.getSize(), repositorio.getStargazersCount(),repositorio.getSubscribersCount(),
                    repositorio.getWatchers(),constituinte));
            
            //Salvar Repositorio no banco
            
            //RepositorioDAO.getINSTANCE().save(new Repositorio(repositorio.getId(),repositorio.getDescription(),
            //  repositorio.getFullName(),repositorio.getName(),url,palavraChave));
        }

        Repositorio retorno = RepositorioDAO.getINSTANCE().read(lstRepositorios.get(1).getId());
        System.out.println("ID : " + String.valueOf(retorno.getId()));
        System.out.println("Nome: " + retorno.getFullName());
        System.out.println(" Descrição: " + retorno.getDescription());

        //Retornar Repositório da base de dados
        return lstRepositorios;

    }
}
