package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class UserDao {

    private static User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }
    private static final String UPDATE_USER_QUERY = "update workshop2.users set username =?, email =?, password=? where id =?;";
    private static final String CREATE_USER_QUERY =
            "INSERT INTO workshop2.users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY ="SELECT * FROM workshop2.users where id = ?;";
    private static final String FINDALL_USER_QUERY ="SELECT * FROM workshop2.users;";

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static User read(int userId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement getUserByIdStatement = conn.prepareStatement(READ_USER_QUERY);
            getUserByIdStatement.setInt(1, userId);
            ResultSet result = getUserByIdStatement.executeQuery();

                while (result.next()) {
                    User user = new User();
                    user.setId(result.getInt(1));
                    user.setEmail(result.getString(2));
                    user.setUserName(result.getString(3));
                    user.setPassword(result.getString(4));
                    return user;
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }


        return null;
    }
    public static void update(User user) throws SQLException {

       String username = user.getUserName();
       String email = user.getEmail();
       String password = user.getPassword();
       String id= String.valueOf(user.getId());

      DbUtil.insert(DbUtil.getConnection(),UPDATE_USER_QUERY, username, email, password, id);
    }

    public static void delete(int userId) throws SQLException {

        int idToDel = userId;

        DbUtil.remove(DbUtil.getConnection(), "workshop2.users", idToDel);
    }
    public static User[] findAll() throws SQLException {


            try (Connection conn = DbUtil.getConnection()) {
                PreparedStatement getUserByIdStatement = conn.prepareStatement(FINDALL_USER_QUERY);
                ResultSet result = getUserByIdStatement.executeQuery();
                User[] users = new User[0];

                while (result.next()) {
                    User u = new User();
                    u.setId(result.getInt(1));
                    u.setEmail(result.getString(2));
                    u.setUserName(result.getString(3));
                    u.setPassword(result.getString(4));
                    users = addToArray(u, users);
                }return users;

            } catch (SQLException e) {
                e.printStackTrace();
            }


            return null;
        }

    public static void main(String[] args) {

    }

}
