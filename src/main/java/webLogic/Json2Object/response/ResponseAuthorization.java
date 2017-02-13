package webLogic.Json2Object.response;

/**
 * Created by PiuPiu on 12.02.2017.
 */
public class ResponseAuthorization {

    private String status; //: "SUCCESS", //или "ERROR"
    private String login;
    private int avatar_id;
    private String note;  // только в случае ошибки


    public ResponseAuthorization() {
    }

    public ResponseAuthorization(String status, String login, int avatar_id) {
        this.status = status;
        this.login = login;
        this.avatar_id = avatar_id;

    }

    public ResponseAuthorization(String status, String note) {
        this.status = status;
        this.note = note;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAvatar_id() {
        return avatar_id;
    }

    public void setAvatar_id(int avatar_id) {
        this.avatar_id = avatar_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
