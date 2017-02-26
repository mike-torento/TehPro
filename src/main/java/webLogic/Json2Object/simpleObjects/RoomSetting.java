package webLogic.Json2Object.simpleObjects;


public class RoomSetting {
    private int room_id; // убрать?
    private String room_name;
    private int players_count;
    private int round_count;
    private int round_time; // mb type Date

    public RoomSetting(int room_id, String room_name, int players_count, int round_count, int round_time) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.players_count = players_count;
        this.round_count = round_count;
        this.round_time = round_time;
    }

    public RoomSetting(String room_name, int players_count, int round_count, int round_time) {
        this.room_name = room_name;
        this.players_count = players_count;
        this.round_count = round_count;
        this.round_time = round_time;
    }

    public RoomSetting() {
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getPlayers_count() {
        return players_count;
    }

    public void setPlayers_count(int players_count) {
        this.players_count = players_count;
    }

    public int getRound_count() {
        return round_count;
    }

    public void setRound_count(int round_count) {
        this.round_count = round_count;
    }

    public int getRound_time() {
        return round_time;
    }

    public void setRound_time(int round_time) {
        this.round_time = round_time;
    }
}
