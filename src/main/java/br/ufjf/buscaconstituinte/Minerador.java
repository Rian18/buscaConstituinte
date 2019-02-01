package br.ufjf.buscaconstituinte;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

/**
 *
 * @author Rian Alves
 */
public class Minerador {

    public void Busca() {
        GitHub github = conexao.getConexao();
        GHRepositorySearchBuilder repo = github.searchRepositories();
        GHRepositorySearchBuilder repos = repo.q(keyword);
        PagedIterable<GHRepository> repositorios = repos.list();

        for (GHRepository repositorio : repositorios) {

            //Salvar Repositorio no banco
            Repositorio reposit = new Repositorio(repositorio.getId(), repositorio.getDescription(),
                    repositorio.getFullName(), repositorio.getName(), repositorio.getUrl().toString(), keyword);
            RepositorioDAO.getINSTANCE().save(reposit);

        }
    }
