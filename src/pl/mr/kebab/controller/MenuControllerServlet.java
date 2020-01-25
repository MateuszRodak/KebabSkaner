package pl.mr.kebab.controller;

import pl.mr.kebab.dao.MenuDAO;
import pl.mr.kebab.dao.RestauracjaDAO;
import pl.mr.kebab.model.Menu;
import pl.mr.kebab.model.Restauracja;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MenuControllerServlet extends AbstractOwnerConrtollerServlet {
    private static final long serialVersionUID = 1L;

    private MenuDAO menuDAO;
    private RestauracjaDAO restauracjaDAO;

    public void setConnectionProperies() {
        restauracjaDAO = new RestauracjaDAO(jdbcURL, jdbcUsername, jdbcPassword);
        menuDAO = new MenuDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String narrowId = request.getParameter("narrowId");
        List<Menu> list;
        if (narrowId != null && !narrowId.equals("")) {
            int id = Integer.parseInt(narrowId);
            list = menuDAO.listForRestauracja(id);
        } else {
            list = menuDAO.listAll();
        }
        request.setAttribute("listMenu", list);
        request.setAttribute("narrowId", narrowId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/menu/MenuList.jsp");
        dispatcher.forward(request, response);
    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/menu/MenuForm.jsp");
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
        Menu existingMenu = menuDAO.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/menu/MenuForm.jsp");
        request.setAttribute("menu", existingMenu);
        request.setAttribute("narrowId", narrowId);

        //dodatkowo lista restauracji
        List<Restauracja> listRest = restauracjaDAO.listAll();
        request.setAttribute("listRest", listRest);

        dispatcher.forward(request, response);

    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idRest = Integer.parseInt(request.getParameter("idRest"));
        String nazwaProduktu = request.getParameter("nazwaProduktu");
        float cena = Float.parseFloat(request.getParameter("cena"));
        String narrowId = request.getParameter("narrowId");

        Menu newMenu = new Menu(idRest, nazwaProduktu, cena);
        menuDAO.insert(newMenu);
        response.sendRedirect(request.getContextPath() + "/menu?operacja=list&narrowId=" + narrowId);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idRest = Integer.parseInt(request.getParameter("idRest"));
        String nazwaProduktu = request.getParameter("nazwaProduktu");
        float cena = Float.parseFloat((request.getParameter("cena")));
        String narrowId = request.getParameter("narrowId");

        Menu menu = new Menu(id, idRest, nazwaProduktu, cena);
        menuDAO.update(menu);
        response.sendRedirect(request.getContextPath() + "/menu?operacja=list&narrowId=" + narrowId);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String narrowId = request.getParameter("narrowId");

        Menu menu = new Menu(id);
        menuDAO.delete(menu);
        response.sendRedirect(request.getContextPath() + "/menu?operacja=list&narrowId=" + narrowId);

    }

    @Override
    protected void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

    }

}