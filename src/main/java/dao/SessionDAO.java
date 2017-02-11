package dao;

import java.sql.*;

public class SessionDAO {

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

    //имя лог файла и состояния игры не храним в бд, так дублирование id сессии
    public static Long createNewSession(String headUser, int numberOfPlayers, int numberOfSteps, int timeOfSteps) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String insertNewSession = "INSERT INTO sessions(statesession,headuser,numberOfPlayers,numberOfSteps,timeOfSteps) VALUES ("
                + 0 + ",'"
                + headUser + "',"
                + numberOfPlayers + ","
                + numberOfSteps + ","
                + timeOfSteps
                + ")";
        
        Long sesID = null;
        String selectNewSessionID = "SELECT currval('sessions_sessionid_seq')";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertNewSession);
            
            ResultSet rs = statement.executeQuery(selectNewSessionID);
            
            while(rs.next()){
                sesID = rs.getLong("sessionid");             
            }

        } catch (SQLException e) {
            System.out.println("Нет подключения к БД");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return sesID;
    }

    
    
    
    
}
