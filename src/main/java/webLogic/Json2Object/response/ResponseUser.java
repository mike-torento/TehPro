package webLogic.Json2Object.response;

import model.User;

public class ResponseUser {
    private User respUser;
    private int hashUser; // будет ли приходить назад?
    private String status;
    private String note;  // описание ошибки, если status = ERROR

    public ResponseUser(User respUser, String status, String note) {
        this.respUser = respUser;
        this.status = status;
        this.note = note;
    }

    public ResponseUser(User respUser, int hashUser, String status) {
        this.respUser = respUser;
        this.hashUser = hashUser;
        this.status = status;
    }

    public ResponseUser() {}

    public User getRespUser() {
        return respUser;
    }

    public void setRespUser(User respUser) {
        this.respUser = respUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
