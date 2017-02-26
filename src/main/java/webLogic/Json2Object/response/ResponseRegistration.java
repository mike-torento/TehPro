package webLogic.Json2Object.response;


public class ResponseRegistration {
    private String status; // "SUCCESS" или "ERROR"
    private String note;

    public ResponseRegistration() {
    }

    public ResponseRegistration(String status, String note) {
        this.status = status;
        this.note = note;
    }

    public ResponseRegistration(String status) {
        this.status = status;
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
