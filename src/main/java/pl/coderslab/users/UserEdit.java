package pl.coderslab.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/user/edit")
public class UserEdit extends HttpServlet {
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

        getServletContext().getRequestDispatcher("/users/useredit.jsp")
                .include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username= request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(username,email,password);
        int id =Integer.parseInt(request.getParameter("id"));
        try {
            UserDao.update(user,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/user/list");
    }
}
