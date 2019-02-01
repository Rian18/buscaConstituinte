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

    public List<Repositorio> Operacao(String palavraChave) throws IOException, ClassNotFoundException, SQLException {

        Conexao conexao = new Conexao();
        String url = "";
        GitHub github = conexao.getConexao();
        GHRepositorySearchBuilder repo = github.searchRepositories();
        GHRepositorySearchBuilder repos = repo.q(palavraChave);
        PagedIterable<GHRepository> repositorios = repos.list();
        List<Repositorio> lstRepositorios = new ArrayList<>();

        for (GHRepository repositorio : repositorios) {

            try {

                System.out.println("Descrição do Repositório : " + repositorio.getDescription());
                System.out.println("Nome Completo : " + repositorio.getFullName());
                System.out.println("Linguagem de Programação : " + repositorio.getLanguage());
                url = repositorio.getHtmlUrl().toString();
                System.out.println(url);
                GHUser user = repositorio.getOwner();
                System.out.println("-----------------||----------------");

                lstRepositorios.add(new Repositorio(repositorio.getId(), repositorio.getDescription(),
                        repositorio.getFullName(), user.getName(), user.getEmail(),url));

                //Salvar Repositorio no banco
               
                RepositorioDAO.getINSTANCE().save(new Repositorio(repositorio.getId(),repositorio.getDescription(),
                        repositorio.getFullName(),repositorio.getName(),url,palavraChave));

            } catch (SQLException ex) {
                Logger.getLogger(Minerador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Minerador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Repositorio retorno = RepositorioDAO.getINSTANCE().read(lstRepositorios.get(1).getId());
        System.out.println("ID : " + String.valueOf(retorno.getId()));
        System.out.println("Nome: " + retorno.getFullName());
        System.out.println(" Descrição: " + retorno.getDescription());

        //Retornar Repositório da base de dados
        return lstRepositorios;

    }
}
