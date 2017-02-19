package webLogic.Json2Object.simpleObjects.states;

import webLogic.Json2Object.simpleObjects.states.EGP;
import webLogic.Json2Object.simpleObjects.states.ESM;

/*
    egp:{
        count: 3,
        cost: 120,
    },
    esm:{
        count: 3,
        cost: 120,
    },
    upgrade_fabric:1, //улучшить 1 фабрику
    build_fabric:1, //построить 1 фабрику
    credit: 2000 //сумма кредита
    */
public class PlayerStates {
    private ESM esm;
    private EGP egp;
    private int upgrade_fabric;
    private int build_fabric;
    private int credit;

    public PlayerStates() {
    }

    public PlayerStates(ESM esm, EGP egp, int upgrade_fabric, int build_fabric, int credit) {
        this.esm = esm;
        this.egp = egp;
        this.upgrade_fabric = upgrade_fabric;
        this.build_fabric = build_fabric;
        this.credit = credit;
    }

    public ESM getEsm() {
        return esm;
    }

    public void setEsm(ESM esm) {
        this.esm = esm;
    }

    public EGP getEgp() {
        return egp;
    }

    public void setEgp(EGP egp) {
        this.egp = egp;
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
