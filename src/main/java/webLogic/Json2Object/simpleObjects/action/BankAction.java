package webLogic.Json2Object.simpleObjects.action;

public class BankAction {
    private boolean egp;
    private boolean esm;
    private boolean credit;
    private int round_to_credit_end;

    public BankAction() {
    }

    public boolean isEgp() {
        return egp;
    }

    public void setEgp(boolean egp) {
        this.egp = egp;
    }

    public boolean isEsm() {
        return esm;
    }

    public void setEsm(boolean esm) {
        this.esm = esm;
    }

    public boolean isCredit() {
        return credit;
    }

    public void setCredit(boolean credit) {
        this.credit = credit;
    }

    public int getRound_to_credit_end() {
        return round_to_credit_end;
    }

    public void setRound_to_credit_end(int round_to_credit_end) {
        this.round_to_credit_end = round_to_credit_end;
    }
}
