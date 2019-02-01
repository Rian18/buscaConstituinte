package action;

import br.ufjf.buscaconstituinte.Minerador;
import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Repositorio;
import persistence.RepositorioDAO;

/**
 *
 * @author Rian Alves
 */
public class BuscaPostAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        try {
            String keyword = request.getParameter("keyword");
            Minerador mineracao = new Minerador();
            mineracao.Operacao(keyword);
            
            List<Repositorio> lstRepositorios = new ArrayList<>();
            lstRepositorios = RepositorioDAO.getINSTANCE().readAll(keyword);
            //Collections.sort(lstRepositorios);
            request.setAttribute("repositorios", lstRepositorios);
            
            RequestDispatcher despachante = request.getRequestDispatcher("/visualizaDados.jsp");
            despachante.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaPostAction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
