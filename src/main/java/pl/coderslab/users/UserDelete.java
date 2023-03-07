package pl.coderslab.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        UserDao userDao = new UserDao();
        User read = null;
        try {
            read = userDao.read(Integer.parseInt(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("user", read);

        getServletContext().getRequestDispatcher("/users/userdelete.jsp")
                .include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("check")!=null) {
                int id = Integer.parseInt(request.getParameter("check"));
                try {
                    UserDao.delete(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }response.sendRedirect("/user/list");
            }else {
            response.sendRedirect("/user/list");
        }

        }
    }

