package webLogic.Json2Object.simpleObjects.action;

import java.util.Map;

public class Actions {
    private String login;
    private int esm_count;
    private int esm_cost;
    private int egp_count;
    private int egp_cost;
    private int build;
    private int upgrade;
    private Map<String,Integer> rewrite;

    public Actions() {
    }

    public Actions(String login, int esm_count, int esm_cost, int egp_count, int egp_cost, int build, int upgrade, Map<String, Integer> rewrite) {
        this.login = login;
        this.esm_count = esm_count;
        this.esm_cost = esm_cost;
        this.egp_count = egp_count;
        this.egp_cost = egp_cost;
        this.build = build;
        this.upgrade = upgrade;
        this.rewrite = rewrite;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getEsm_count() {
        return esm_count;
    }

    public void setEsm_count(int esm_count) {
        this.esm_count = esm_count;
    }

    public int getEsm_cost() {
        return esm_cost;
    }

    public void setEsm_cost(int esm_cost) {
        this.esm_cost = esm_cost;
    }

    public int getEgp_count() {
        return egp_count;
    }

    public void setEgp_count(int egp_count) {
        this.egp_count = egp_count;
    }

    public int getEgp_cost() {
        return egp_cost;
    }

    public void setEgp_cost(int egp_cost) {
        this.egp_cost = egp_cost;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

    public int getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }

    public Map<String, Integer> getRewrite() {
        return rewrite;
    }

    public void setRewrite(Map<String, Integer> rewrite) {
        this.rewrite = rewrite;
    }
}
