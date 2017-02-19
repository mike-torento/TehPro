package webLogic.Json2Object.simpleObjects.states;


public class ESM {
    private int count;
    private int cost;

    public ESM() {
    }

    public ESM(int count, int cost) {
        this.count = count;
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
