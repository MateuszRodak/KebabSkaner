package pl.mr.kebab.controller;

import pl.mr.kebab.dao.AdresDAO;
import pl.mr.kebab.model.Adres;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdresControllerServlet extends AbstractOwnerConrtollerServlet {
    private static final long serialVersionUID = 1L;

    private AdresDAO adresDAO;

    public void setConnectionProperies() {
        adresDAO = new AdresDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Adres> list = adresDAO.listAll();
        request.setAttribute("listAdres", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/adres/AdresList.jsp");
        dispatcher.forward(request, response);
    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/adres/AdresForm.jsp");
        dispatcher.forward(request, response);
    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Adres existingAdres = adresDAO.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/adres/AdresForm.jsp");
        request.setAttribute("adres", existingAdres);
        dispatcher.forward(request, response);

    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String miejscowosc = request.getParameter("miejscowosc");
        String ulica = request.getParameter("ulica");
        String nrLokalu = request.getParameter("nrLokalu");
        String kod = request.getParameter("kod");

        Adres newAdres = new Adres(miejscowosc, ulica, nrLokalu, kod);
        adresDAO.insert(newAdres);
        response.sendRedirect(request.getContextPath() + "/adres?operacja=list");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String miejscowosc = request.getParameter("miejscowosc");
        String ulica = request.getParameter("ulica");
        String nrLokalu = request.getParameter("nrLokalu");
        String kod = request.getParameter("kod");

        Adres adres = new Adres(id, miejscowosc, ulica, nrLokalu, kod);
        adresDAO.update(adres);
        response.sendRedirect(request.getContextPath() + "/adres?operacja=list");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Adres adres = new Adres(id);
        adresDAO.delete(adres);
        response.sendRedirect(request.getContextPath() + "/adres?operacja=list");

    }
}