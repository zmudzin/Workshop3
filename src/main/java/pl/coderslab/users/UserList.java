package pl.coderslab.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/user/list")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess= request.getSession();
//        List<User> users = new ArrayList<>();
//        try {
//            User [] user = UserDao.findAll();
//            for (User u: user)
//            {
//                users.add(new User(u.getUserName(), u.getEmail(),u.getPassword()));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


        try {
            UserDao userDao = new UserDao();
            sess.setAttribute("users", userDao.findAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        getServletContext().getRequestDispatcher("/users/result.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
