package webLogic.Json2Object.simpleObjects;


public class Room {
    private int ID;
    private String name;
    private String room_status;
    private int players_count;
    private int rounds_count;
    private int round_time;
    private String room_creator;
    private String[] players;

    public Room() {}

    public Room(int ID, String name, String room_status, int players_count, int rounds_count, int round_time, String room_creator, String[] players) {
        this.ID = ID;
        this.name = name;
        this.room_status = room_status;
        this.players_count = players_count;
        this.rounds_count = rounds_count;
        this.round_time = round_time;
        this.room_creator = room_creator;
        this.players = players;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom_status() {
        return room_status;
    }

    public void setRoom_status(String room_status) {
        this.room_status = room_status;
    }

    public int getPlayers_count() {
        return players_count;
    }

    public void setPlayers_count(int players_count) {
        this.players_count = players_count;
    }

    public int getRounds_count() {
        return rounds_count;
    }

    public void setRounds_count(int rounds_count) {
        this.rounds_count = rounds_count;
    }

    public int getRound_time() {
        return round_time;
    }

    public void setRound_time(int round_time) {
        this.round_time = round_time;
    }

    public String getRoom_creator() {
        return room_creator;
    }

    public void setRoom_creator(String room_creator) {
        this.room_creator = room_creator;
    }

    public String[] getPlayers() {
        return players;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }
}
