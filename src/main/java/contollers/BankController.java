package contollers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Bank;
import model.Player;
import model.User;
import org.apache.taglibs.standard.tei.ForEachTEI;

public class BankController {

    private Bank bank; //(тут имеем текущии значения уровня, егп, цены и т.п)
    private List<Player> playersList;

    private static final Double[][] PC = new Double[5][4];
    private static final Double[][] LVL = new Double[5][5];

    //При старте игры
    public BankController(List<User> userList) {
        playersList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            playersList.add(new Player(userList.get(i)));
        }
        bank = new Bank(userList.size());

        PC[0][0] = 1.0;
        PC[0][1] = 800.0;
        PC[0][2] = 3.0;
        PC[0][3] = 6500.0;

        PC[1][0] = 1.5;
        PC[1][1] = 650.0;
        PC[1][2] = 2.6;
        PC[1][3] = 6000.0;

        PC[2][0] = 2.0;
        PC[2][1] = 500.0;
        PC[2][2] = 2.0;
        PC[2][3] = 5500.0;

        PC[3][0] = 2.5;
        PC[3][1] = 400.0;
        PC[3][2] = 1.5;
        PC[3][3] = 5000.0;

        PC[4][0] = 3.0;
        PC[4][1] = 300.0;
        PC[4][2] = 1.0;
        PC[4][3] = 4500.0;

        LVL[0][0] = 0.33333;
        LVL[0][1] = 0.66666;
        LVL[0][2] = 0.83332;
        LVL[0][3] = 0.091665;
        LVL[0][4] = 0.08333;

        LVL[1][0] = 0.25;
        LVL[1][1] = 0.33333;
        LVL[1][2] = 0.25;
        LVL[1][3] = 0.08333;
        LVL[1][4] = 0.08333;

        LVL[2][0] = 0.08333;
        LVL[2][1] = 0.25;
        LVL[2][2] = 0.33333;
        LVL[2][3] = 0.25;
        LVL[2][4] = 0.08333;

        LVL[3][0] = 0.08333;
        LVL[3][1] = 0.08333;
        LVL[3][2] = 0.25;
        LVL[3][3] = 0.33333;
        LVL[3][4] = 0.25;

        LVL[4][0] = 0.08333;
        LVL[4][1] = 0.08333;
        LVL[4][2] = 0.16666;
        LVL[4][3] = 0.33333;
        LVL[4][4] = 0.33333;

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

        Double rnd = Math.random();
        Double sum = LVL[oldLevel - 1][0];

        for (int i = 1; i < 5; i++) {
            if (sum < rnd) {
                sum = sum + LVL[oldLevel - 1][i];
            } else {
                newLevel = i;
            }
        }
        if (newLevel != bank.getCurrentlevel()) {
            bank.setCurrentlevel(newLevel);
        }

    }

    //получить прибыль
    //списываем налоги
    public void writeOffMonthlyOutgoings() {
        for (int i = 0; i < playersList.size(); i++) {
            if (!playersList.get(i).isBankrupt()) {
                int nalog = 300 * playersList.get(i).getNumberOfESM() + 500 * playersList.get(i).getNumberOfEGP()
                        + 1000 * playersList.get(i).getNumberOfReadyStandartFactories() + 1500 * playersList.get(i).getNumberOfUniversalFactories();
            }
        }
    }

    //проверяем банкротов
    public void checkBankrupts() {
        for (int i = 0; i < playersList.size(); i++) {
            if (playersList.get(i).getMoney() < 0) {
                playersList.get(i).setBankrupt(true);
            }
        }
    }

    //устанавливаем количество и цены на ресурсы
    public void setPriseAndCountResourses() {
        int notBankrupt = 0;
        for (int i = 0; i < playersList.size(); i++) {
            if (!playersList.get(i).isBankrupt()) {
                notBankrupt++;
            }
        }
        for (int i = 0; i < PC.length; i++) {
            bank.setCountESM((int) Math.round(PC[bank.getCurrentlevel() - 1][0] * notBankrupt));
            bank.setMinPriceForESM(PC[bank.getCurrentlevel() - 1][1].intValue());

            bank.setCountEGP((int) Math.round(PC[bank.getCurrentlevel() - 1][2] * notBankrupt));
            bank.setMaxPriceForEGP(PC[bank.getCurrentlevel() - 1][3].intValue());
        }
    }

    //переработаьь ЕСМ в ЕГП 
    public boolean recycleESM(User user, int numberOfESM, String TypeOfFactory) {
        for (Player player : playersList) {
            if (player.getUser().getLogin().equals(user.getLogin())) {
                if (player.getNumberOfESM() >= numberOfESM) {
                    if (TypeOfFactory.equals("standart")) {
                        player.setNumberOfESM(player.getNumberOfESM() - numberOfESM);
                        player.setMoney(player.getMoney() - numberOfESM * 2000);
                        return true;
                    }
                    if (TypeOfFactory.equals("universal")) {
                        if (numberOfESM % 2 == 0) {
                            player.setNumberOfESM(player.getNumberOfESM() - numberOfESM);
                            player.setMoney(player.getMoney() - numberOfESM * 1500);
                            return true;
                        } else {
                            player.setNumberOfESM(player.getNumberOfESM() - numberOfESM);
                            player.setMoney(player.getMoney() - (numberOfESM - 1) * 1500 - 2000);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //подали заявку на покупку ЕСМ (юзер, количество, стоимость)
    public void setTheRequiredNumberAndPriceOfEsmForPlayer(User user, int numberOfESM, int priceForESM) {
        for (int i = 0; i < playersList.size(); i++) {
            if (playersList.get(i).getUser().getLogin().equals(user.getLogin())) {
                playersList.get(i).setTheRequiredNumberOfESM(numberOfESM);
                playersList.get(i).setTheRequiredPriceOfESM(priceForESM);
            }

        }
    }

    //подать заявку (цену и количество) для продажи ЕГП
    public void setPriceAndNumberOfEGP(User user, int numberOfEGP, int priceForEGP) {
        for (Player player : playersList) {
            if (player.getUser().getLogin().equals(user.getLogin())) {
                player.setSellNumberOfEGP(numberOfEGP);
                player.setSellPriceOfEGP(priceForEGP);
            }
        }
    }

    public int findMaxPriceForESM() {
        int maxPrice = 0;
        for (Player player : playersList) {
            if (player.getTheRequiredPriceOfESM() > maxPrice) {
                maxPrice = player.getTheRequiredPriceOfESM();
            }
        }
        return maxPrice;
    }

    public int findMinPriceForEGP() {
        int minPrice = Integer.MAX_VALUE - 2;
        for (Player player : playersList) {
            if (player.getSellPriceOfEGP() < minPrice) {
                minPrice = player.getSellPriceOfEGP();
            }
        }
        return minPrice;
    }

    //банк обработает запросы на продажу ЕСМ пользователям
    public void processRequestsForSaleESM() {
        for (Player player : playersList) {
            if (player.getTheRequiredPriceOfESM() == findMaxPriceForESM()) {
                if (bank.getCountESM() > player.getTheRequiredNumberOfESM()) {
                    bank.setCountESM(bank.getCountEGP() - player.getTheRequiredNumberOfESM());
                    player.setNumberOfESM(player.getNumberOfESM() + player.getTheRequiredNumberOfESM());
                    player.setTheRequiredNumberOfESM(0);
                    player.setTheRequiredPriceOfESM(-1);
                }
            }
        }
        if (bank.getCountESM() > 0) {
            for (Player player : playersList) {
                if (player.getTheRequiredPriceOfESM() == findMaxPriceForESM()) {
                    player.setNumberOfESM(player.getNumberOfESM() + bank.getCountESM());
                    bank.setCountESM(0);
                    player.setTheRequiredNumberOfESM(0);
                    player.setTheRequiredPriceOfESM(-1);
                }
            }
        }

    }
    
    //банк обрабатывает запросы на покупку ЕГП у пользователе
    public void processRequestForPurchaseEGP() {
        for (Player player : playersList) {
            if (player.getSellPriceOfEGP() == findMinPriceForEGP()) {
                if (bank.getCountEGP() > player.getSellNumberOfEGP()) {
                    bank.setCountEGP(bank.getCountEGP() - player.getSellNumberOfEGP());
                    player.setMoney(player.getMoney() + player.getSellNumberOfEGP() * player.getSellPriceOfEGP());
                    player.setSellNumberOfEGP(0);
                    player.setSellPriceOfEGP(Integer.MAX_VALUE);

                }
            }
        }

        if (bank.getCountEGP() > 0) {
            for (Player player : playersList) {
                if(player.getSellPriceOfEGP()== findMinPriceForEGP()){
                    player.setMoney(player.getMoney() + bank.getCountEGP() * player.getSellPriceOfEGP());
                    bank.setCountEGP(0);
                    player.setSellNumberOfEGP(0);
                    player.setSellPriceOfEGP(Integer.MAX_VALUE);
                }
            }
        }

    }

    //взять ссуду
    public void getLoan(User user){
        
    }
    
    //выплатить ежемесячный проент по ссуде
   public void payInterestOnTheLoan(){
       for (Player player : playersList) {
               if(player.getLoan()>0){
                   player.setMoney(player.getMoney() - (int)Math.round(player.getLoan() * 0.01));
               }
       }
   }
    
}
