package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.GameSession;
import model.User;

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
    public static Long createNewSession(String name, int numberOfPlayers, int numberOfSteps, int timeOfSteps){
        Connection dbConnection = null;
        Statement statement = null;
        String insertNewSession = "INSERT INTO sessions(name, statesession, numberOfPlayers, numberOfSteps, timeOfSteps) VALUES ('"
                + name + "',"
                + "'AVAILABLE',"
                + numberOfPlayers + ","
                + numberOfSteps + ","
                + timeOfSteps
                + ") RETURNING sessionid";
        
        Long sesID = null;
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            
            
            ResultSet rs = statement.executeQuery(insertNewSession);
            
            while(rs.next()){
                sesID = rs.getLong("sessionid");             
            }

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
                System.out.println("Не закрыто подключение к БД ");
            }
        }
        return sesID;
    }
    
    public static void addNewPlayer(String userLogin, Long sessionID){
        Connection dbConnection = null;
        Statement statement = null;
        String insertNewPlayer = "INSERT INTO users_sessions(login, sessionid) VALUES ('"
                + userLogin + "',"
                + sessionID
                + ")";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertNewPlayer);  

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
                System.out.println("Не закрыто подключение к БД ");
            }
        }
    }
    
    public static List<GameSession> getAllSessions(){
        Connection dbConnection = null;
        Statement statement = null;
        List<GameSession> gameSessions = new ArrayList<>();
        String selectSessions = "Select * from sessions";
        
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            Statement statement1 = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectSessions);
            while (rs.next()) {
                GameSession game = new GameSession();
                game.setSessionID(rs.getLong("sessionid"));
                game.setName(rs.getString("name"));
                game.setState(rs.getString("statesession"));
                game.setNumberOfPlayers(rs.getInt("numberofplayers"));
                game.setNumberOfSteps(rs.getInt("numberofsteps"));
                game.setTimeOfSteps(rs.getInt("timeofsteps"));  
                String selectUsers = "Select * from users_sessions where sessionid =" + game.getSessionID();
                ResultSet users = statement1.executeQuery(selectUsers);
                while (users.next()) {
                    game.addNewGamer(dao.UserDAO.getUser(users.getString("login")));
                }
                
                gameSessions.add(game);

            }
        } catch (SQLException e) {
            System.out.println("Невозможно получить список комнат");
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
        return gameSessions;
}
    
    public static List<Long> getRoomsForUser(String userLogin){
        Connection dbConnection = null;
        Statement statement = null;
        List<Long> gameSessionIDs = new ArrayList<>();
        String selectSessions = "Select sessionid from users_sessions where login='" +userLogin+ "'";
        
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectSessions);
            while (rs.next()) {
                gameSessionIDs.add(rs.getLong("sessionid"));
            }
        } catch (SQLException e) {
            System.out.println("Невозможно получить список комнат");
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
        return gameSessionIDs;
    } 
    
    public static void editRoomStatus(Long id, String status){
        Connection dbConnection = null;
        Statement statement = null;
        String editStatus = "Update sessions set statesession='"+ status + "' where sessionid = " + id;
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(editStatus);  

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
                System.out.println("Не закрыто подключение к БД ");
            }
        }
    }
}
