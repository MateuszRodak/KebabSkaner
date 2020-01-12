package pl.mr.kebab.controller;

import pl.mr.kebab.dao.AdresDAO;
import pl.mr.kebab.dao.ListaDodatkowDAO;
import pl.mr.kebab.dao.PorcjaDAO;
import pl.mr.kebab.dao.MenuDAO;
import pl.mr.kebab.model.Adres;
import pl.mr.kebab.model.ListaDodatkow;
import pl.mr.kebab.model.Menu;
import pl.mr.kebab.model.Porcja;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListaDodatkowControllerServlet extends AbstractOwnerConrtollerServlet {
    private static final long serialVersionUID = 1L;

    private ListaDodatkowDAO listaDodatkowDAO;

    public void setConnectionProperies() {
        listaDodatkowDAO = new ListaDodatkowDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<ListaDodatkow> list = listaDodatkowDAO.listAll();
        request.setAttribute("listListaDodatkow", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/listaDodatkow/ListaDodatkowList.jsp");
        dispatcher.forward(request, response);
    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/listaDodatkow/ListaDodatkowForm.jsp");
        dispatcher.forward(request, response);
    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ListaDodatkow existingListaDodatkow = listaDodatkowDAO.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/listaDodatkow/ListaDodatkowForm.jsp");
        request.setAttribute("listaDodatkow", existingListaDodatkow);
        dispatcher.forward(request, response);

    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nazwa = request.getParameter("nazwa");

        ListaDodatkow newListaDodatkow = new ListaDodatkow(nazwa);
        listaDodatkowDAO.insert(newListaDodatkow);
        response.sendRedirect(request.getContextPath() + "/listaDodatkow?operacja=list");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ListaDodatkow listaDodatkow = new ListaDodatkow(id);
        listaDodatkowDAO.delete(listaDodatkow);
        response.sendRedirect(request.getContextPath() + "/listaDodatkow?operacja=list");
    }
}