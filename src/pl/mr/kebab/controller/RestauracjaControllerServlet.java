package pl.mr.kebab.controller;

import pl.mr.kebab.dao.AdresDAO;
import pl.mr.kebab.dao.RestauracjaDAO;
import pl.mr.kebab.model.Adres;
import pl.mr.kebab.model.Restauracja;
import pl.mr.kebab.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class RestauracjaControllerServlet extends AbstractOwnerConrtollerServlet {
    private static final long serialVersionUID = 1L;

    private RestauracjaDAO restauracjaDAO;
    private AdresDAO adresDAO;

    public void init() {
        super.init();
        restauracjaDAO = new RestauracjaDAO(jdbcURL, jdbcOwnerUsername, jdbcOwnerPassword);
        adresDAO = new AdresDAO(jdbcURL, jdbcOwnerUsername, jdbcOwnerPassword);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Restauracja> list = restauracjaDAO.listAll();
        request.setAttribute("listRest", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/restauracja/RestauracjaList.jsp");
        dispatcher.forward(request, response);
    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/restauracja/RestauracjaForm.jsp");

        //dodatkowo lista adresow
        List<Adres> listAdr = adresDAO.listAll();
        request.setAttribute("listAdres", listAdr);

        dispatcher.forward(request, response);
    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Restauracja existingRestauracja = restauracjaDAO.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/restauracja/RestauracjaForm.jsp");
        request.setAttribute("restauracja", existingRestauracja);

        //dodatkowo lista adresow
        List<Adres> listAdr = adresDAO.listAll();
        request.setAttribute("listAdres", listAdr);

        dispatcher.forward(request, response);

    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nazwa = request.getParameter("nazwa");
        int idAdres = Integer.parseInt(request.getParameter("idAdres"));
        Time godzOtw = Utils.convertToTime(request.getParameter("godzOtw"));
        Time godzZam = Utils.convertToTime(request.getParameter("godzZam"));
        boolean dowoz = Boolean.parseBoolean(request.getParameter("dowoz"));

        Restauracja newRestauracja = new Restauracja(nazwa, idAdres, godzOtw, godzZam, dowoz);
        restauracjaDAO.insert(newRestauracja);
        response.sendRedirect(request.getContextPath() + "/restauracja?operacja=list");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nazwa = request.getParameter("nazwa");
        int idAdres = Integer.parseInt(request.getParameter("idAdres"));
        Time godzOtw = Utils.convertToTime(request.getParameter("godzOtw"));
        Time godzZam = Utils.convertToTime(request.getParameter("godzZam"));
        boolean dowoz = Boolean.parseBoolean(request.getParameter("dowoz"));

        Restauracja restauracja = new Restauracja(id, nazwa, idAdres, godzOtw, godzZam, dowoz);
        restauracjaDAO.update(restauracja);
        response.sendRedirect(request.getContextPath() + "/restauracja?operacja=list");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Restauracja restauracja = new Restauracja(id);
        restauracjaDAO.delete(restauracja);
        response.sendRedirect(request.getContextPath() + "/restauracja?operacja=list");

    }
}