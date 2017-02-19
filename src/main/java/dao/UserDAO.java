package dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import model.User;

public class UserDAO {

    static String url = "jdbc:postgresql://localhost:5432/TP";
    static String name = "postgres";
    static String password = "12345";

    private static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Невозможно получить коннект к базе");
        }
        return connection;
    }

    public static void addUser(User newUser) {
        Connection dbConnection = null;
        Statement statement = null;
        String insertUser = "INSERT INTO users(login,avatarid) VALUES ('"
                + newUser.getLogin() + "',"
                + newUser.getAvatarID() + ")";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertUser);
        } catch (SQLException e) {
            System.out.println("Нет подключения к БД");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                System.out.println("Не закрыто подключение к БД");
            }
        }

    }

    public static User getUser(String login) {
        Connection dbConnection = null;
        Statement statement = null;
        User user = new User();
        String selectUser = "Select * from users where login='" + login+  "'";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectUser);
            while (rs.next()) {
                user.setLogin(rs.getString("login"));
                user.setAvatarID(rs.getInt("avatarid"));
                return user;

            }
        } catch (SQLException e) {
            System.out.println("Такого пользователя не существует");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                System.out.println("Не закрыто подключение к БД ");
            }
        }
        return null;
    }

    //TO DO написать даод ля статистики
//    public static Map<String, Integer> getUserStatistics(String login) throws SQLException {
//        Connection dbConnection = null;
//        Statement statement = null;
//        Map<String, Integer> statisticsMap = new HashMap<String, Integer>();
//
//        String selectAllGame = "Select count(*) From users_sessions ";
//        return null;
//    }
}
