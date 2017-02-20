package webLogic.Json2Object.request;

import webLogic.Json2Object.simpleObjects.action.PlayerAction;

import java.util.List;

public class RequestGameArea {
    private long room_id;
    private String action;
    private String login;
    private List<PlayerAction> action_data;

    public RequestGameArea() {
    }

    public RequestGameArea(long room_id, String action, String login, List<PlayerAction> action_data) {
        this.room_id = room_id;
        this.action = action;
        this.login = login;
        this.action_data = action_data;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<PlayerAction> getAction_data() {
        return action_data;
    }

    public void setAction_data(List<PlayerAction> action_data) {
        this.action_data = action_data;
    }
}
