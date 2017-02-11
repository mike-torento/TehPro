package model;

public class Player {
  private User user;
  private int money;
  private int numberOfEGP;
  private int numberOfESM;
  private int numberOfStandartFactories; 
  private int numberOfUniversalFactories;
  private int numberOfReadyStandartFactories;
  private int numberOfReadyUniversalFactories;

  
  //только для создания нового игрока, состояния по дефолту
    public Player(User user) {
        this.user = user;
        money = 10000;
        numberOfEGP = 2;
        numberOfESM = 2;
        numberOfStandartFactories = 2;
        numberOfReadyStandartFactories = 2;
    }
    
    

    public Player() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNumberOfEGP() {
        return numberOfEGP;
    }

    public void setNumberOfEGP(int numberOfEGP) {
        this.numberOfEGP = numberOfEGP;
    }

    public int getNumberOfESM() {
        return numberOfESM;
    }

    public void setNumberOfESM(int numberOfESM) {
        this.numberOfESM = numberOfESM;
    }

    public int getNumberOfStandartFactories() {
        return numberOfStandartFactories;
    }

    public void setNumberOfStandartFactories(int numberOfStandartFactories) {
        this.numberOfStandartFactories = numberOfStandartFactories;
    }

    public int getNumberOfUniversalFactories() {
        return numberOfUniversalFactories;
    }

    public void setNumberOfUniversalFactories(int numberOfUniversalFactories) {
        this.numberOfUniversalFactories = numberOfUniversalFactories;
    }

    public int getNumberOfReadyStandartFactories() {
        return numberOfReadyStandartFactories;
    }

    public void setNumberOfReadyStandartFactories(int numberOfReadyStandartFactories) {
        this.numberOfReadyStandartFactories = numberOfReadyStandartFactories;
    }

    public int getNumberOfReadyUniversalFactories() {
        return numberOfReadyUniversalFactories;
    }

    public void setNumberOfReadyUniversalFactories(int numberOfReadyUniversalFactories) {
        this.numberOfReadyUniversalFactories = numberOfReadyUniversalFactories;
    }
    
    
  
  
  
  
  
}
