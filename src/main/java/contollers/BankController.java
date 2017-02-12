package contollers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Bank;
import model.Player;
import model.User;

public class BankController {

    private Bank bank; //(тут имеем текущии значения уровня, егп, цены и т.п)
    private List<Player> playersList;

    //При старте игры
    public BankController(List<User> userList) {
        playersList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            playersList.add(new Player(userList.get(i)));
        }
        bank = new Bank(userList.size());

    }

    public BankController() {

    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public void setCurrentLevel() {
        int oldLevel = bank.getCurrentlevel();
        int newLevel = 3;
        Double[][] lvl = new Double[5][5];
        lvl[0][0] = 0.33333;
        lvl[0][1] = 0.66666;
        lvl[0][2] = 0.83332;
        lvl[0][3] = 0.091665;
        lvl[0][4] = 0.08333;

        lvl[1][0] = 0.25;
        lvl[1][1] = 0.33333;
        lvl[1][2] = 0.25;
        lvl[1][3] = 0.08333;
        lvl[1][4] = 0.08333;

        lvl[2][0] = 0.08333;
        lvl[2][1] = 0.25;
        lvl[2][2] = 0.33333;
        lvl[2][3] = 0.25;
        lvl[2][4] = 0.08333;

        lvl[3][0] = 0.08333;
        lvl[3][1] = 0.08333;
        lvl[3][2] = 0.25;
        lvl[3][3] = 0.33333;
        lvl[3][4] = 0.25;

        lvl[4][0] = 0.08333;
        lvl[4][1] = 0.08333;
        lvl[4][2] = 0.16666;
        lvl[4][3] = 0.33333;
        lvl[4][4] = 0.33333;

        Double rnd = Math.random();
        Double sum = lvl[oldLevel - 1][0];

        for (int i = 1; i < 5; i++) {
            if (sum < rnd) {
                sum = sum + lvl[oldLevel - 1][i];
            } else {
                newLevel = i;
            }

        }
        if (newLevel != bank.getCurrentlevel()) {
            bank.setCurrentlevel(newLevel);
        }

    }


    public void writeOffMonthlyOutgoings(){
        for (int i = 0; i < playersList.size(); i++) {
            playersList.get(i).setMoney(i);
            
        }
    
    }

}
