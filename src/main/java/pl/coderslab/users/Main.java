package pl.coderslab.users;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDao.update(new User("zenek", "matoł", "Ichuj"), 3);
    }

}
