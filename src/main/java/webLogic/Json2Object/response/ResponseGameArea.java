package webLogic.Json2Object.response;

import model.Bank;
import webLogic.Json2Object.simpleObjects.action.BankAction;

public class ResponseGameArea {
    private long room_id;
    private int rounds;
    private int current_round;
    private String login; // получить из юзера в плеере
    private String player_status;
    private Bank bank; //берется из текущей сессии
    private BankAction action_response;


}
