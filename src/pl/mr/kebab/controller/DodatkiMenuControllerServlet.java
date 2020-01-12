package pl.mr.kebab.controller;


import pl.mr.kebab.dao.*;
import pl.mr.kebab.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DodatkiMenuControllerServlet extends AbstractOwnerConrtollerServlet {
    private static final long serialVersionUID = 1L;

    private DodatkiMenuDAO dodatkiMenuDAO;
    private MenuDAO menuDAO;
    private ListaDodatkowDAO listaDodatkowDAO;

    public void setConnectionProperies() {
        dodatkiMenuDAO = new DodatkiMenuDAO(jdbcURL, jdbcUsername, jdbcPassword);
        menuDAO = new MenuDAO(jdbcURL, jdbcUsername, jdbcPassword);
        listaDodatkowDAO = new ListaDodatkowDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String narrowId = request.getParameter("narrowId");
        List<DodatkiMenu> list;
        if (narrowId != null && !narrowId.equals("")) {
            int id = Integer.parseInt(narrowId);
            list = dodatkiMenuDAO.listForMenu(id);
        } else {
            list = dodatkiMenuDAO.listAll();
        }
        request.setAttribute("listDodatkiMenu", list);
        request.setAttribute("narrowId", narrowId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/dodatkiMenu/DodatkiMenuList.jsp");
        dispatcher.forward(request, response);
    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/dodatkiMenu/DodatkiMenuForm.jsp");
        String narrowId = request.getParameter("narrowId");
        request.setAttribute("narrowId", narrowId);

        //dodatkowo lista restauracji
        List<Menu> listMenu = menuDAO.listAll();
        request.setAttribute("listMenu", listMenu);

        List<ListaDodatkow> listaDodatkow = listaDodatkowDAO.listAll();
        request.setAttribute("listListaDodatkow", listaDodatkow);

        dispatcher.forward(request, response);
    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String narrowId = request.getParameter("narrowId");
        DodatkiMenu existingDodatkiMenu = dodatkiMenuDAO.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/dodatkiMenu/DodatkiMenuForm.jsp");
        request.setAttribute("idMenu", existingDodatkiMenu);
        request.setAttribute("idListyDodatkow", existingDodatkiMenu);
        request.setAttribute("narrowId", narrowId);

        //dodatkowo lista restauracji
        List<Menu> listMenu = menuDAO.listAll();
        request.setAttribute("listMenu", listMenu);

        List<ListaDodatkow> listListaDodatkow = listaDodatkowDAO.listAll();
        request.setAttribute("listListaDodatkow", listListaDodatkow);

        dispatcher.forward(request, response);

    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idMenu = Integer.parseInt(request.getParameter("idMenu"));
        int idListyDodatkow = Integer.parseInt(request.getParameter("idListyDodatkow"));
        String narrowId = request.getParameter("narrowId");

        DodatkiMenu newDodatkiMenu = new DodatkiMenu(idMenu, idListyDodatkow);
        dodatkiMenuDAO.insert(newDodatkiMenu);
        response.sendRedirect(request.getContextPath() + "/dodatkiMenu?operacja=list&narrowId=" + narrowId);
    }

    @Override
    protected void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

    }


    protected void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String narrowId = request.getParameter("narrowId");

        DodatkiMenu dodatkiMenu = new DodatkiMenu(id);
        dodatkiMenuDAO.delete(dodatkiMenu);
        response.sendRedirect(request.getContextPath() + "/dodatkiMenu?operacja=list&narrowId=" + narrowId);
    }
}