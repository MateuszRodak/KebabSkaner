package pl.mr.kebab.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public abstract class AbstractOwnerConrtollerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected String jdbcURL;
    protected String jdbcOwnerUsername;
    protected String jdbcOwnerPassword;
    protected String jdbcGuestUsername;
    protected String jdbcGuestPassword;

    public void init() {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcOwnerUsername = getServletContext().getInitParameter("jdbcOwnerUsername");
        jdbcOwnerPassword = getServletContext().getInitParameter("jdbcOwnerPassword");
        jdbcGuestUsername = getServletContext().getInitParameter("jdbcGuestUsername");
        jdbcGuestPassword = getServletContext().getInitParameter("jdbcGuestPassword");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String operacja = request.getParameter("operacja");
        try {
            switch (operacja) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insert(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    update(request, response);
                    break;
                case "list":
                    list(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected abstract void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException;

    protected abstract void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;

    protected abstract void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException;

    protected abstract void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;

    protected abstract void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;

    protected abstract void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
}
