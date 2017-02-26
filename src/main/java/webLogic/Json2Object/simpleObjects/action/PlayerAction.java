package webLogic.Json2Object.simpleObjects.action;

import webLogic.Json2Object.simpleObjects.states.EGP;
import webLogic.Json2Object.simpleObjects.states.ESM;
import webLogic.Json2Object.simpleObjects.states.Process;

public class PlayerAction {
    private EGP egp;
    private ESM esm;
    private Process process; // fabric inf
    private int credit;

    public PlayerAction() {
    }

    public PlayerAction(EGP egp, ESM esm, Process process, int credit) {
        this.egp = egp;
        this.esm = esm;
        this.process = process;
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

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
