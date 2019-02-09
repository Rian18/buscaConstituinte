package action;

import br.ufjf.buscaconstituinte.Minerador;
import br.ufjf.comparator.MediaComparator;
import br.ufjf.controller.Action;
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
import br.ufjf.model.Repositorio;
import br.ufjf.persistence.RepositorioDAO;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Rian Alves
 */
public class BuscaPostAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        try {
            String constituinte = request.getParameter("keyword");
            Minerador mineracao = new Minerador();
            
            
            List<Repositorio> lstRepositorios;
            lstRepositorios = mineracao.Operacao(constituinte); 
            //lstRepositorios = RepositorioDAO.getINSTANCE().readAll(keyword);
            //Collections.sort(lstRepositorios);
            Comparator c = Collections.reverseOrder(new MediaComparator());
            Collections.sort(lstRepositorios, c);
            request.setAttribute("repositorios", lstRepositorios);
            Integer totalAcima = 0;
            for(Repositorio r: lstRepositorios){
                if(r.getMedia()==0){
                    break;
                }
                totalAcima++;
            }
            
            request.setAttribute("totalAcima", totalAcima);
            RequestDispatcher despachante = request.getRequestDispatcher("/visualizaDados.jsp");
            despachante.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaPostAction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
