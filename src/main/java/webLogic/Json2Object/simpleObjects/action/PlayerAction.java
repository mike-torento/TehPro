package webLogic.Json2Object.simpleObjects.action;

import webLogic.Json2Object.simpleObjects.states.EGP;
import webLogic.Json2Object.simpleObjects.states.ESM;

public class PlayerAction {
    private EGP egp;
    private ESM esm;
    private int upgrade_fabric;
    private int build_fabric;
    private int credit;
    // еще добавить производство егп


    public PlayerAction() {
    }

    public PlayerAction(EGP egp, ESM esm, int upgrade_fabric, int build_fabric, int credit) {
        this.egp = egp;
        this.esm = esm;
        this.upgrade_fabric = upgrade_fabric;
        this.build_fabric = build_fabric;
        this.credit = credit;
    }

    public EGP getEgp() {
        return egp;
    }

    public void setEgp(EGP egp) {
        this.egp = egp;
    }

    public ESM getEsm() {
        return esm;
    }

    public void setEsm(ESM esm) {
        this.esm = esm;
    }

    public int getUpgrade_fabric() {
        return upgrade_fabric;
    }

    public void setUpgrade_fabric(int upgrade_fabric) {
        this.upgrade_fabric = upgrade_fabric;
    }

    public int getBuild_fabric() {
        return build_fabric;
    }

    public void setBuild_fabric(int build_fabric) {
        this.build_fabric = build_fabric;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
