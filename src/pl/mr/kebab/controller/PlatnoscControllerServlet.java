package pl.mr.kebab.controller;


import pl.mr.kebab.dao.PlatnoscDAO;
import pl.mr.kebab.dao.PorcjaDAO;
//import pl.mr.kebab.dao.MenuDAO;
import pl.mr.kebab.dao.RestauracjaDAO;
//import pl.mr.kebab.model.Menu;
import pl.mr.kebab.model.Platnosc;
import pl.mr.kebab.model.Porcja;
import pl.mr.kebab.model.Restauracja;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PlatnoscControllerServlet extends AbstractOwnerConrtollerServlet {
    private static final long serialVersionUID = 1L;

    private PlatnoscDAO platnoscDAO;
    private RestauracjaDAO restauracjaDAO;

    public void setConnectionProperies() {
        restauracjaDAO = new RestauracjaDAO(jdbcURL, jdbcUsername, jdbcPassword);
        platnoscDAO = new PlatnoscDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String narrowId = request.getParameter("narrowId");
        List<Platnosc> list;
        if (narrowId != null && !narrowId.equals("")) {
            int id = Integer.parseInt(narrowId);
            list = platnoscDAO.listForRestauracja(id);
        } else {
            list = platnoscDAO.listAll();
        }
        request.setAttribute("listPlatnosc", list);
        request.setAttribute("narrowId", narrowId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/platnosc/PlatnoscList.jsp");
        dispatcher.forward(request, response);
    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/platnosc/PlatnoscForm.jsp");
        String narrowId = request.getParameter("narrowId");
        request.setAttribute("narrowId", narrowId);

        //dodatkowo lista restauracji
        List<Restauracja> listRest = restauracjaDAO.listAll();
        request.setAttribute("listRest", listRest);

        dispatcher.forward(request, response);
    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String narrowId = request.getParameter("narrowId");
        Platnosc existingPlatnosc = platnoscDAO.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/platnosc/PlatnoscForm.jsp");
        request.setAttribute("platnosc", existingPlatnosc);
        request.setAttribute("narrowId", narrowId);

        //dodatkowo lista restauracji
        List<Restauracja> listRest = restauracjaDAO.listAll();
        request.setAttribute("listRest", listRest);

        dispatcher.forward(request, response);

    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idRest = Integer.parseInt(request.getParameter("idRest"));
        String rodzajPlatnosci = request.getParameter("rodzajPlatnosci");
        String narrowId = request.getParameter("narrowId");

        Platnosc newPlatnosc = new Platnosc(idRest, rodzajPlatnosci);
        platnoscDAO.insert(newPlatnosc);
        response.sendRedirect(request.getContextPath() + "/platnosc?operacja=list&narrowId=" + narrowId);
    }

    @Override
    protected void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

    }


    protected void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String narrowId = request.getParameter("narrowId");

        Platnosc platnosc = new Platnosc(id);
        platnoscDAO.delete(platnosc);
        response.sendRedirect(request.getContextPath() + "/platnosc?operacja=list&narrowId=" + narrowId);
    }
}