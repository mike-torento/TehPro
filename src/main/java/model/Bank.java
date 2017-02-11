package model;

public class Bank {

    private int countESM;
    private int minPriceForESM;
    
    private int countEGP;
    private int maxPriceForEGP;
    
    private int currentlevel;

    //конструктор для начальной инициализации
    public Bank(int numberOfPlayers){
        currentlevel = 3;
        countESM = 2*numberOfPlayers;
        minPriceForESM = 500;
        countEGP = 2*numberOfPlayers;
        maxPriceForEGP=5500;      
    }
    
    public Bank(){
        
    }
    
    public int getCountESM() {
        return countESM;
    }

    public void setCountESM(int countESM) {
        this.countESM = countESM;
    }

    public int getMinPriceForESM() {
        return minPriceForESM;
    }

    public void setMinPriceForESM(int minPriceForESM) {
        this.minPriceForESM = minPriceForESM;
    }

    public int getCountEGP() {
        return countEGP;
    }

    public void setCountEGP(int countEGP) {
        this.countEGP = countEGP;
    }

    public int getMaxPriceForEGP() {
        return maxPriceForEGP;
    }

    public void setMaxPriceForEGP(int maxPriceForEGP) {
        this.maxPriceForEGP = maxPriceForEGP;
    }

    public int getCurrentlevel() {
        return currentlevel;
    }

    public void setCurrentlevel(int currentlevel) {
        this.currentlevel = currentlevel;
    }


}
