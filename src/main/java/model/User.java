
package model;

public class User {
    private String login;
    private int avatarID;

    public User(String login, int avatarID) {
        this.login = login;
        this.avatarID = avatarID;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public int getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(int avatarID) {
        this.avatarID = avatarID;
    }
    
    
}
