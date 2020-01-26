package pl.mr.kebab.controller;

import pl.mr.kebab.dao.MenuDAO;
import pl.mr.kebab.model.DodatkiMenu;
import pl.mr.kebab.model.ListaDodatkow;
import pl.mr.kebab.model.Menu;
import pl.mr.kebab.model.Porcja;
import pl.mr.kebab.model.Restauracja;
import pl.mr.kebab.model.enums.OpisPorcja;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String porcja = (request.getParameter("porcja"));
        String dodatek = (request.getParameter("dodatek"));
        String otwarte = (request.getParameter("otwarte"));

        boolean allowSearch = false;

        if (clicked != null && !clicked.equals("")) {
            Menu menu = new Menu();

            //parametr wymagany
            if (nazwaProduktu != null && !nazwaProduktu.equals("")) {
                menu.setNazwaProduktu(nazwaProduktu);
                allowSearch = true;
            }

            if (cena != null && !cena.equals("") && !cena.equals("0.0")) {
                menu.setCena(Float.parseFloat(cena));
            }

            if (dowoz != null && !dowoz.equals("")) {
                Restauracja restauracja = new Restauracja();
                restauracja.setDowoz(Boolean.parseBoolean(dowoz));
                menu.setRestauracja(restauracja);
            }

            if (porcja != null && !porcja.equals("")) {
                Porcja p = new Porcja();
                p.setOpis(OpisPorcja.getEnum(porcja));
                List<Porcja> porcjaList = new ArrayList<>();
                porcjaList.add(p);
                menu.setPorcjaList(porcjaList);
            }

            if (dodatek != null && !dodatek.equals("")) {
                DodatkiMenu dodatkiMenu = new DodatkiMenu();
                ListaDodatkow listaDodatkow = new ListaDodatkow();
                listaDodatkow.setNazwa(dodatek);
                dodatkiMenu.setListaDodatkow(listaDodatkow);
                List<DodatkiMenu> dodatkiMenuList = new ArrayList<>();
                dodatkiMenuList.add(dodatkiMenu);
                menu.setDodatkiMenuList(dodatkiMenuList);
            }

            if (otwarte != null && !otwarte.equals("")) {
                // bo mogl juz byc dowoz
                if (menu.getRestauracja() == null) {
                    Restauracja restauracja = new Restauracja();
                    restauracja.setRestOpen(Boolean.parseBoolean(otwarte));
                    menu.setRestauracja(restauracja);
                } else {
                    menu.getRestauracja().setRestOpen(Boolean.parseBoolean(otwarte));
                }
            }

            if (allowSearch) {
                List<Menu> list = menuDAO.searchManyTables(menu);
                request.setAttribute("listMenu", list);
            }
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
