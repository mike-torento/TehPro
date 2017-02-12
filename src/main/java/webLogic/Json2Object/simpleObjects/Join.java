package webLogic.Json2Object.simpleObjects;

/**
 * Created by PiuPiu on 12.02.2017.
 */
public class Join {
    private String login;
    private int room_id;

    public Join() {}

    public Join(String login, int room_id) {
        this.login = login;
        this.room_id = room_id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
}
