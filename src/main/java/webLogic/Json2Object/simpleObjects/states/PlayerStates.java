package webLogic.Json2Object.simpleObjects.states;

import webLogic.Json2Object.simpleObjects.states.EGP;
import webLogic.Json2Object.simpleObjects.states.ESM;

/*
    player_data: {
        egp: 3,
        esm: 4,
        money: 4000,
        fabric 3, //работает
        fabric_works: 1 //всего есть
        auto_fabric: 2,
        auto_fabric_works: 1,
        builded_fabric: 1, //количество строящихся фабрик
        upgraded_fabric: 1, // количество улучшающихся фабрик
    }
    */
public class PlayerStates {
    private int EGP;
    private int ESM;
    private int money;
    private int fabric;
    private int fabric_works;
    private int auto_fabric;
    private int auto_fabric_works;
    private int builder_fabric;
    private int upgrade_fabric;
}
