package webLogic.controllers;


import contollers.BankController;
import model.Bank;
import model.GameSession;
import model.Player;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import webLogic.Json2Object.ActionConstant;
import webLogic.Json2Object.RoomsStorage;
import webLogic.Json2Object.request.RequestGameArea;
import webLogic.Json2Object.response.ResponseGameArea;
import webLogic.Json2Object.response.ResponseGameRoom;
import webLogic.Json2Object.simpleObjects.action.Actions;
import webLogic.Json2Object.simpleObjects.states.Check;
import webLogic.Json2Object.simpleObjects.states.EGP;
import webLogic.Json2Object.simpleObjects.states.ESM;
import webLogic.Json2Object.simpleObjects.states.Process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class GameAreaController {

    private List<RequestGameArea> players_states = new ArrayList<RequestGameArea>();
    private List<Actions> actions;
    private int count;
    private Check check = new Check(ActionConstant.CHECK_STATUS_WAITING);
    private Player winner;

    @RequestMapping(value = "/collect", consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    void collect(@RequestBody RequestGameArea rga) {
        count = RoomsStorage.getInstance().getRoomForID(rga.getRoom_id()).getNumberOfPlayers();
        if (!players_states.contains(rga)) {
            players_states.add(rga);
        }
        if (players_states.size() == count) {
            actions = new ArrayList<Actions>();
            for (RequestGameArea cur : players_states) {
                final Process process = cur.getAction_data().getProcess();
                actions.add(new Actions(
                        cur.getLogin(),
                        cur.getAction_data().getEsm().getCount(),
                        cur.getAction_data().getEsm().getCost(),
                        cur.getAction_data().getEgp().getCount(),
                        cur.getAction_data().getEgp().getCost(),
                        process.getBuild_fabric(),
                        process.getUpgrade_fabric(),
                        new HashMap<String, Integer>() {{
                            put("standart", process.getFabric());
                            put("universal", process.getAuto_fabric());
                        }}
                ));
                //playerHandler(cur);
            }
            handler(actions,rga.getRoom_id());
            actions.clear();
            players_states.clear();
            count = 0;
        }
    }
   // @RequestMapping(value = "/nextround",produces = MediaType.APPLICATION_JSON_VALUE)
    public
   // @ResponseBody
    void handler(List<Actions> actions, long room_id){
        boolean flag;
        GameSession game = RoomsStorage.getInstance().getRoomForID(room_id);
        if (!actions.isEmpty()){
            if (RoomsStorage.getInstance().getRoomForID(room_id).nextStep(actions)){
                check.setCheck(ActionConstant.CHECK_STATUS_READY);
            }
            else {
                winner = RoomsStorage.getInstance().getRoomForID(room_id).endGame();
                check.setCheck(ActionConstant.ROOM_STATUS_FINISHED);
            }
        }

    }

    @RequestMapping(value = "/check", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Check getCheck(){
        return check;
    }

    @RequestMapping(value = "/getwinner", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Player getWinner(){
        return winner;
    }

    @RequestMapping(value = "/nextround",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseGameRoom next(@RequestBody long room_id){
        for (GameSession cur: RoomsStorage.getInstance().getRoms()){
            if (cur.getSessionID()==room_id){
                List<GameSession> action_data = new ArrayList<GameSession>();
                action_data.add(cur);
                return new ResponseGameRoom(ActionConstant.STATUS_SUCCESS,ActionConstant.ROOM_STATUS_ACTIVE,action_data,null,null);
            }
        }
        return null; // прописать ошибку
    }

//    @RequestMapping(value = "/nextround", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public
//    @ResponseBody
//    void playerHandler(RequestGameArea rga) {
//        GameSession gs = RoomsStorage.getInstance().getRoomForID(rga.getRoom_id());
//        BankController bankController = gs.getBankController();
//        Bank bank = bankController.getBank();
//        EGP egp = rga.getAction_data().getEgp();
//        ESM esm = rga.getAction_data().getEsm();
//        Process process = rga.getAction_data().getProcess();
//        Player player = null; // убрать, перенести действия с игроком внутри цикл, чтобы работать с плеером из списка
//        for (Player cur : bankController.getPlayersList()) {
//            if (cur.getUser().getLogin().equals(rga.getLogin()))
//                player = cur;
//        }
//        // работа банка и прочее
//        if (egp != null) {
//            bankController.setPriceAndNumberOfEGP(
//                    player.getUser(),
//                    egp.getCount(),
//                    egp.getCost()
//            );
//        }
//        if (esm != null) {
//            bankController.setTheRequiredNumberAndPriceOfEsmForPlayer(
//                    player.getUser(),
//                    esm.getCount(),
//                    esm.getCost()
//            );
//        }
//
//        if (process != null) { // работа с фабриками
//            if (process.getFabric() != 0) {
//                bankController.recycleESM(
//                        player.getUser(),
//                        process.getFabric(),
//                        "standart"
//                );
//            }
//            if (process.getAuto_fabric() != 0) {
//                bankController.recycleESM(
//                        player.getUser(),
//                        process.getAuto_fabric(),
//                        "universal"
//                );
//            }
//            if (process.getBuild_fabric() != 0) {
//            }
//            if (process.getUpgrade_fabric() != 0) {
//            }
//        }
//
//    }
}
