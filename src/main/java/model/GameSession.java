package model;

import contollers.BankController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameSession {

    private Long sessionID;
    List<User> userList;
    BankController bankController;
    private int numberOfPlayers;
    private int numberOfSteps;
    private int timeOfSteps;
    private int state; // 0 не начата, 1 начата и длится, 2 завершена но не до конца, 3 полностю завершена 
    // log file
    // state file

//использовать только при создании
    public GameSession(User headUser, int numberOfPlayers, int numberOfSteps, int timeOfSteps) {
        try {
            userList = new ArrayList<>();
            userList.add(headUser);
            this.sessionID = dao.SessionDAO.createNewSession(headUser.getLogin(), numberOfPlayers, numberOfSteps, timeOfSteps);
            this.numberOfPlayers = numberOfPlayers;
            this.numberOfSteps = numberOfSteps;
            this.timeOfSteps = timeOfSteps;
            this.state = 0; //не начата

        } catch (SQLException e) {
            System.out.println("Невозможно создать игровую сессию");
        }

    }

    public GameSession() {
    }

    public Long getSessionID() {
        return sessionID;
    }

    public void setSessionID(Long sessionID) {
        this.sessionID = sessionID;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public int getTimeOfSteps() {
        return timeOfSteps;
    }

    public void setTimeOfSteps(int timeOfSteps) {
        this.timeOfSteps = timeOfSteps;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void addNewGamer(User newUser) {
        if (userList.size() < numberOfPlayers) {
            userList.add(newUser);
        } else {
            System.out.println("Комната заполнена");
        }
    }

    public boolean checkReadyStatus(){
        return (userList.size()== numberOfPlayers);
    }
    
    public void startGame(){
        bankController = new BankController(userList);
        state = 1; //игра началась и длится
    }

}
