package pl.mr.kebab.controller;

import pl.mr.kebab.dao.PorcjaDAO;
import pl.mr.kebab.dao.MenuDAO;
import pl.mr.kebab.model.Menu;
import pl.mr.kebab.model.Porcja;
import pl.mr.kebab.model.enums.JednostkaPorcja;
import pl.mr.kebab.model.enums.OpisPorcja;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class PorcjaControllerServlet extends AbstractOwnerConrtollerServlet {
    private static final long serialVersionUID = 1L;

    private PorcjaDAO porcjaDAO;
    private MenuDAO menuDAO;

    public void setConnectionProperies() {
        menuDAO = new MenuDAO(jdbcURL, jdbcUsername, jdbcPassword);
        porcjaDAO = new PorcjaDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String narrowId = request.getParameter("narrowId");
        List<Porcja> list;
        if (narrowId != null && !narrowId.equals("")) {
            int id = Integer.parseInt(narrowId);
            list = porcjaDAO.listForMenu(id);
        } else {
            list = porcjaDAO.listAll();
        }
        request.setAttribute("listPorcja", list);
        request.setAttribute("narrowId", narrowId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/porcja/PorcjaList.jsp");
        dispatcher.forward(request, response);
    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/porcja/PorcjaForm.jsp");
        String narrowId = request.getParameter("narrowId");
        request.setAttribute("narrowId", narrowId);

        //dodatkowo lista menu
        List<Menu> listMenu = menuDAO.listAll();
        request.setAttribute("listMenu", listMenu);

        dispatcher.forward(request, response);
    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String narrowId = request.getParameter("narrowId");
        Porcja existingPorcja = porcjaDAO.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/porcja/PorcjaForm.jsp");
        request.setAttribute("porcja", existingPorcja);
        request.setAttribute("narrowId", narrowId);

        //dodatkowo lista menu
        List<Menu> listMenu = menuDAO.listAll();
        request.setAttribute("listMenu", listMenu);

        dispatcher.forward(request, response);

    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idMenu = Integer.parseInt(request.getParameter("idMenu"));
        int wielkosc = Integer.parseInt(request.getParameter("wielkosc"));
        JednostkaPorcja jednostka = JednostkaPorcja.getEnum(request.getParameter("jednostka"));
        OpisPorcja opis = OpisPorcja.getEnum(request.getParameter("opis"));
        String narrowId = request.getParameter("narrowId");

        Porcja newPorcja = new Porcja(idMenu, wielkosc, jednostka, opis);
        porcjaDAO.insert(newPorcja);
        response.sendRedirect(request.getContextPath() + "/porcja?operacja=list&narrowId=" + narrowId);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idMenu = Integer.parseInt(request.getParameter("idMenu"));
        int wielkosc = Integer.parseInt(request.getParameter("wielkosc"));
        JednostkaPorcja jednostka = JednostkaPorcja.getEnum(request.getParameter("jednostka"));
        OpisPorcja opis = OpisPorcja.getEnum(request.getParameter("opis"));
        String narrowId = request.getParameter("narrowId");

        Porcja porcja = new Porcja(id, idMenu, wielkosc, jednostka, opis);
        porcjaDAO.update(porcja);
        response.sendRedirect(request.getContextPath() + "/porcja?operacja=list&narrowId=" + narrowId);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String narrowId = request.getParameter("narrowId");

        Porcja porcja = new Porcja(id);
        porcjaDAO.delete(porcja);
        response.sendRedirect(request.getContextPath() + "/porcja?operacja=list&narrowId=" + narrowId);
    }

    @Override
    protected void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

    }

}