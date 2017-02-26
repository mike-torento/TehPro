package webLogic.Json2Object.simpleObjects.states;

public class Process {
    private int upgrade_fabric; //улучшить 1 фабрику
    private int build_fabric; //построить 1 фабрику
    private int fabric; // сколько произведет обыная
    private int auto_fabric; // сколько произведет автоматическая

    public Process() {
    }

    public Process(int upgrade_fabric, int build_fabric, int fabric, int auto_fabric) {
        this.upgrade_fabric = upgrade_fabric;
        this.build_fabric = build_fabric;
        this.fabric = fabric;
        this.auto_fabric = auto_fabric;
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

    public int getFabric() {
        return fabric;
    }

    public void setFabric(int fabric) {
        this.fabric = fabric;
    }

    public int getAuto_fabric() {
        return auto_fabric;
    }

    public void setAuto_fabric(int auto_fabric) {
        this.auto_fabric = auto_fabric;
    }
}




