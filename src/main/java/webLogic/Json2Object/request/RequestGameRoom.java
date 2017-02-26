package webLogic.Json2Object.request;

import webLogic.Json2Object.simpleObjects.RoomSetting;

public class RequestGameRoom {
    private String login;
    private String action;
    private RoomSetting action_data; // возможно массив сеттингов, надо додумать

    public RequestGameRoom(String login, String action, RoomSetting action_data) {
        this.login = login;
        this.action = action;
        this.action_data = action_data;
    }

    public RequestGameRoom() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public RoomSetting getAction_data() {
        return action_data;
    }

    public void setAction_data(RoomSetting action_data) {
        this.action_data = action_data;
    }
}
