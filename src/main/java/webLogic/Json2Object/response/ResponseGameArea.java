package webLogic.Json2Object.response;

import model.Bank;
import model.Player;
import java.util.List;

public class ResponseGameArea {
    private long room_id;
    private int rounds;
    private int current_round;
    private Bank bank;
    private List<Player> players_states;

    public ResponseGameArea() {
    }

    public ResponseGameArea(long room_id, int rounds, int current_round, Bank bank, List<Player> players_states) {
        this.room_id = room_id;
        this.rounds = rounds;
        this.current_round = current_round;
        this.bank = bank;
        this.players_states = players_states;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getCurrent_round() {
        return current_round;
    }

    public void setCurrent_round(int current_round) {
        this.current_round = current_round;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Player> getPlayers_states() {
        return players_states;
    }

    public void setPlayers_states(List<Player> players_states) {
        this.players_states = players_states;
    }
}