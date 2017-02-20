//package webLogic.controllers;
//
//
//import contollers.BankController;
//import model.Bank;
//import model.GameSession;
//import model.Player;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import webLogic.Json2Object.ActionConstant;
//import webLogic.Json2Object.RoomsStorage;
//import webLogic.Json2Object.request.RequestGameArea;
//import webLogic.Json2Object.response.ResponseGameArea;
//
//@Controller
//public class GameAreaController {
//
//    @RequestMapping(value = "", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    public
//    @ResponseBody
//    ResponseGameArea gameState(@RequestBody RequestGameArea rga){
//        GameSession gs = null;
//        Player player = null;
//        for (GameSession cur: RoomsStorage.getInstance().getRoms()){
//            if (cur.getSessionID()==rga.getRoom_id()) gs = cur;
//        }
//        if (gs == null) return null;
//
//        BankController bankController = gs.getBankController();
//        for (Player cur: bankController.getPlayersList()){
//            if (cur.getUser().getLogin().equals(rga.getLogin()))
//                player = cur;
//        }
//
//        Bank bank = bankController.getBank();
//
//        if (rga.getAction().equalsIgnoreCase(ActionConstant.GAME_AREA_BID)){
//            bankController.
//
//
//
//        }
//
//
//
//    }
//}
