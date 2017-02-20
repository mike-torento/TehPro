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

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameAreaController {

    private List<RequestGameArea> players_states = null;
    private int count;

    @RequestMapping(value = "/collect", consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    void collect(@RequestBody RequestGameArea rga){
        count = RoomsStorage.getInstance().getRoomForID(rga.getRoom_id()).getNumberOfPlayers();
        players_states = new ArrayList<RequestGameArea>();
        if (players_states.size()==count) {
            for (RequestGameArea cur: players_states){
                playerHandler(cur);
            }
        }

    }

    @RequestMapping(value = "/nextround", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseGameArea playerHandler(RequestGameArea rga) {
        GameSession gs = RoomsStorage.getInstance().getRoomForID(rga.getRoom_id());
        BankController bankController = gs.getBankController();
        Bank bank = bankController.getBank();
        Player player = null;
        for (Player cur : bankController.getPlayersList()) {
            if (cur.getUser().getLogin().equals(rga.getLogin()))
                player = cur;
        }
        if (rga.getAction().equalsIgnoreCase(ActionConstant.GAME_AREA_BID)) {
            bankController.setTheRequiredNumberAndPriceOfEsmForPlayer(
                    player.getUser(),
                    rga.getAction_data().getEsm().getCount(),
                    rga.getAction_data().getEsm().getCost());
            bankController.setPriceAndNumberOfEGP(
                    player.getUser(),
                    rga.getAction_data().getEgp().getCount(),
                    rga.getAction_data().getEgp().getCost());
            // улучшение / построение фабрик; перерпботка есм
        }

    }
}
