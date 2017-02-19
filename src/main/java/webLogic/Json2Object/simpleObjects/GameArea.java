package webLogic.Json2Object.simpleObjects;


import webLogic.Json2Object.simpleObjects.states.PlayerStates;

import java.util.List;
    /*
    room_id:123,
    action: "UPDATE" //или "BID" в случае подачи заявок пользователем
    login:"my login",
    action_data:{ // есть только при "BID"
        egp:
        {
            count: 3,
            cost: 120,
        },
        esm:
        {
            count: 3,
            cost: 120,
        },
        upgrade_fabric:1, //улучшить 1 фабрику
        build_fabric:1, //построить 1 фабрику
        credit: 2000 //сумма кредита
     }
        */

public class GameArea {
    private int room_id;
    private String action;
    private String login;
    private List<PlayerStates> action_data; // mb Map<Login,PlayerStates> ?

    public GameArea() {
    }

    public GameArea(int room_id, String action, String login) {
        this.room_id = room_id;
        this.action = action;
        this.login = login;
    }

    public GameArea(int room_id, String action, String login, List<PlayerStates> action_data) {
        this.room_id = room_id;
        this.action = action;
        this.login = login;
        this.action_data = action_data;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
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

    public List<PlayerStates> getAction_data() {
        return action_data;
    }

    public void setAction_data(List<PlayerStates> action_data) {
        this.action_data = action_data;
    }
}
