package webLogic.Json2Object.response;


import model.GameSession;

import java.util.List;

public class ResponseGameRoom {
    private String action_status;
    private String action;
    private List<GameSession> action_data;
    private List<GameSession> rooms;
    private List<GameSession> unfinished_rooms;
    // текущий круг
    //

    public ResponseGameRoom() {
    }

    public ResponseGameRoom(String action_status, String action, List<GameSession> action_data, List<GameSession> rooms, List<GameSession> unfinished_rooms) {
        this.action_status = action_status;
        this.action = action;
        this.action_data = action_data;
        this.rooms = rooms;
        this.unfinished_rooms = unfinished_rooms;
    }

    public List<GameSession> getAction_data() {
        return action_data;
    }

    public void setAction_data(List<GameSession> action_data) {
        this.action_data = action_data;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<GameSession> getRooms() {
        return rooms;
    }

    public void setRooms(List<GameSession> rooms) {
        this.rooms = rooms;
    }

    public List<GameSession> getUnfinished_rooms() {
        return unfinished_rooms;
    }

    public void setUnfinished_rooms(List<GameSession> unfinished_rooms) {
        this.unfinished_rooms = unfinished_rooms;
    }
}
