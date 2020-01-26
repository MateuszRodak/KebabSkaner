package pl.mr.kebab.controller;

import pl.mr.kebab.dao.MenuDAO;
import pl.mr.kebab.model.Menu;
import pl.mr.kebab.model.Restauracja;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SzukajControllerServlet extends AbstractOwnerConrtollerServlet {

    private static final long serialVersionUID = 1L;

    private MenuDAO menuDAO;

    @Override
    public void setConnectionProperies() {
        menuDAO = new MenuDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        response.sendRedirect(request.getContextPath() + "/szukaj?operacja=search");
    }

    @Override
    protected void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String clicked = (request.getParameter("clicked"));
        String nazwaProduktu = request.getParameter("nazwaProduktu");
        String cena = (request.getParameter("cena"));
        String dowoz = (request.getParameter("dowoz"));

        if (clicked != null && !clicked.equals("")) {
            Menu menu = new Menu();
            if (nazwaProduktu != null && !nazwaProduktu.equals("")) {
                menu.setNazwaProduktu(nazwaProduktu);
            }
            if (cena != null && !cena.equals("") && !cena.equals("0.0")) {
                menu.setCena(Float.parseFloat(cena));
            }

            if (dowoz != null && !dowoz.equals("")) {
                Restauracja restauracja = new Restauracja();
                restauracja.setDowoz(Boolean.parseBoolean(dowoz));
                menu.setRestauracja(restauracja);
            }

            List<Menu> list = menuDAO.searchManyTables(menu);
            request.setAttribute("listMenu", list);

            request.setAttribute("menu", menu);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/szukaj/Szukaj.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }

    @Override
    protected void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

    }

    @Override
    protected void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

    }

    @Override
    protected void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

    }

    @Override
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

    }

}
