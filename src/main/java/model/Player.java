package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private User user;
    private int money;
    private int numberOfEGP;
    private int numberOfESM;
    private int numberOfStandartFactories;
    private int numberOfUniversalFactories;
    private int numberOfReadyStandartFactories;
    private int numberOfReadyUniversalFactories;
    private boolean Bankrupt;

    private List<Loan> loan ; 

    private int theRequiredNumberOfESM;
    private int theRequiredPriceOfESM;

    private int sellNumberOfEGP;
    private int sellPriceOfEGP;

    //только для создания нового игрока, состояния по дефолту
    public Player(User user) {
        this.user = user;
        money = 10000;
        numberOfEGP = 2;
        numberOfESM = 4;
        numberOfStandartFactories = 2;
        numberOfReadyStandartFactories = 2;
        Bankrupt = false;
        loan = new ArrayList<>();
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

    public int getTheRequiredNumberOfESM() {
        return theRequiredNumberOfESM;
    }

    public void setTheRequiredNumberOfESM(int theRequiredNumberOfESM) {
        this.theRequiredNumberOfESM = theRequiredNumberOfESM;
    }

    public int getTheRequiredPriceOfESM() {
        return theRequiredPriceOfESM;
    }

    public void setTheRequiredPriceOfESM(int theRequiredPriceOfESM) {
        this.theRequiredPriceOfESM = theRequiredPriceOfESM;
    }

    public int getSellNumberOfEGP() {
        return sellNumberOfEGP;
    }

    public void setSellNumberOfEGP(int sellNumberOfEGP) {
        this.sellNumberOfEGP = sellNumberOfEGP;
    }

    public int getSellPriceOfEGP() {
        return sellPriceOfEGP;
    }

    public void setSellPriceOfEGP(int sellPriceOfEGP) {
        this.sellPriceOfEGP = sellPriceOfEGP;
    }

    public boolean isBankrupt() {
        return Bankrupt;
    }

    public void setBankrupt(boolean Bankrupt) {
        this.Bankrupt = Bankrupt;
    }

    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }

    public int getSumLoans(){
        int sum = 0;
        for (Loan loan1 : loan) {
            if(loan1.getTheEndOfLoan()>0){
                sum = sum + loan1.getLoan();
            }
        }
        return sum;
    }
    
    public void addLoan(int summ){
        loan.add(new Loan(summ));
    }

    public class Loan{
        private int loan;
        private int theEndOfLoan;

        public Loan(int loan) {
            this.loan = loan;
            this.theEndOfLoan = 12;
        }
        
        public Loan(){
 
        }
        

        public int getLoan() {
            return loan;
        }

        public void setLoan(int loan) {
            this.loan = loan;
        }

        public int getTheEndOfLoan() {
            return theEndOfLoan;
        }

        public void setTheEndOfLoan(int theEndOfLoan) {
            this.theEndOfLoan = theEndOfLoan;
        }
        
        
    }
    
    
}
