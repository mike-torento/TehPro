package model;

import contollers.BankController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import webLogic.Json2Object.ActionConstant;

public class GameSession {

    private Long sessionID;
    List<User> userList = new ArrayList<>();
    BankController bankController;
    private String name;
    private int numberOfPlayers;
    private int numberOfSteps;
    private int timeOfSteps;
    private String state; 
    // log file
    // state file

//использовать только при создании
    public GameSession(User headUser, String name, int numberOfPlayers, int numberOfSteps, int timeOfSteps) {
            userList.add(headUser);
            this.sessionID = dao.SessionDAO.createNewSession(name, numberOfPlayers, numberOfSteps, timeOfSteps);
            this.name = name;
            this.numberOfPlayers = numberOfPlayers;
            this.numberOfSteps = numberOfSteps;
            this.timeOfSteps = timeOfSteps;
            this.state = ActionConstant.ROOM_STATUS_AVAILABLE; //не начата
            dao.SessionDAO.addNewPlayer(headUser.getLogin(), sessionID);
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public BankController getBankController() {
        return bankController;
    }

    public void setBankController(BankController bankController) {
        this.bankController = bankController;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNewGamer(User newUser) {
        if (userList.size() < numberOfPlayers) {
            userList.add(newUser);
            dao.SessionDAO.addNewPlayer(newUser.getLogin(), sessionID);
        } else {
            System.out.println("Комната заполнена");
        }
    }

    public boolean checkReadyStatus(){
        return (userList.size()== numberOfPlayers);
    }
    
    public void startGame(){
        bankController = new BankController(userList);
        state = ActionConstant.ROOM_STATUS_ACTIVE;
        dao.SessionDAO.editRoomStatus(getSessionID(), ActionConstant.ROOM_STATUS_ACTIVE);
    }

}
