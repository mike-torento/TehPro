package webLogic.Json2Object.simpleObjects;


public class Join {
    private String login;
    private long room_id;

    public Join() {
    }

    public Join(String login, int room_id) {
        this.login = login;
        this.room_id = room_id;
    }
    
    public String getLogin() {
        return login;
    }

    public long getRoom_id() {
        return room_id;
    }

}
