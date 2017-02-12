package webLogic.Json2Object.response;


import webLogic.Json2Object.simpleObjects.Room;

import java.util.List;

public class ResponseGameRoom {
    private String action_status;
    private String action;
    private List<Room> action_data;
    private List<Room> rooms;
    private List<Room> unfinished_rooms;

    public ResponseGameRoom() {}

    public ResponseGameRoom(String action_status, String action, List<Room> action_data, List<Room> rooms, List<Room> unfinished_rooms) {
        this.action_status = action_status;
        this.action = action;
        this.action_data = action_data;
        this.rooms = rooms;
        this.unfinished_rooms = unfinished_rooms;
    }

    public List<Room> getAction_data() {
        return action_data;
    }

    public void setAction_data(List<Room> action_data) {
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getUnfinished_rooms() {
        return unfinished_rooms;
    }

    public void setUnfinished_rooms(List<Room> unfinished_rooms) {
        this.unfinished_rooms = unfinished_rooms;
    }
}
