package webLogic.Json2Object.response;

import model.Bank;
import model.Player;
import webLogic.Json2Object.simpleObjects.action.BankAction;

import java.util.List;

public class ResponseGameArea {
    private long room_id;
    private int rounds;
    private int current_round;
    private String login; // получить из юзера в плеере
    private String player_status;
    private Bank bank; //берется из текущей сессии
    private BankAction action_response; // пока не надо
    private Player player_data; // по каждому юзеру
    private List<Player> players_states; // пока не надо

    public ResponseGameArea() {
    }

    public ResponseGameArea(long room_id, int rounds, int current_round, String login, String player_status, Bank bank, BankAction action_response, Player player_data, List<Player> players_states) {
        this.room_id = room_id;
        this.rounds = rounds;
        this.current_round = current_round;
        this.login = login;
        this.player_status = player_status;
        this.bank = bank;
        this.action_response = action_response;
        this.player_data = player_data;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPlayer_status() {
        return player_status;
    }

    public void setPlayer_status(String player_status) {
        this.player_status = player_status;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BankAction getAction_response() {
        return action_response;
    }

    public void setAction_response(BankAction action_response) {
        this.action_response = action_response;
    }

    public Player getPlayer_data() {
        return player_data;
    }

    public void setPlayer_data(Player player_data) {
        this.player_data = player_data;
    }

    public List<Player> getPlayers_states() {
        return players_states;
    }

    public void setPlayers_states(List<Player> players_states) {
        this.players_states = players_states;
    }
}