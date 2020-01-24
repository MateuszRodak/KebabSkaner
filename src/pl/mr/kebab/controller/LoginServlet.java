package pl.mr.kebab.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        //tu można użyć bazy do sprawdzania użytkownikow
        if ((name.equals("owner") && password.equals("owner")) || (name.equals("guest") && password.equals("guest"))) {
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            session.setAttribute("message", "Jesteś zalogowany jako " + name);
            if (name.equals("owner")) {
                request.getRequestDispatcher("edit.jsp").include(request, response);
                session.setAttribute("role", "owner");
            } else {
                session.setAttribute("role", "guest");
                response.sendRedirect(request.getContextPath() + "/search.jsp");
            }
        } else {
            request.setAttribute("message", "Sorry, username or password error!");
            request.getRequestDispatcher("login.jsp").include(request, response);
            //wyloguj!
            request.getSession().invalidate();
        }
    }
}