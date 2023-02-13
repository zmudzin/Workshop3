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

        try {
            User [] user = UserDao.findAll();
            System.out.println(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        List<User> list = new ArrayList<>();
//        UserDao userDao = new UserDao();
//        try {
//            request.setAttribute("list", userDao.findAll());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//        System.out.println(list);
        getServletContext().getRequestDispatcher("/users/list.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
