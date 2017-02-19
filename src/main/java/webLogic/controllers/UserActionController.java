package webLogic.controllers;

import dao.UserDAO;
import model.GameSession;
import model.User;
import org.apache.taglibs.standard.tag.common.xml.IfTag;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import webLogic.Json2Object.ActionConstant;
import webLogic.Json2Object.request.RequestGameRoom;
import webLogic.Json2Object.response.ResponseGameRoom;
import webLogic.Json2Object.simpleObjects.Join;
import webLogic.Json2Object.simpleObjects.RoomSetting;
import webLogic.Json2Object.RoomsStorage;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserActionController {

    @RequestMapping(value = "/createroom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseGameRoom createRoom(@RequestBody RequestGameRoom reqGameRoom) { // связать с классами Мишы
        User user = UserDAO.getUser(reqGameRoom.getLogin());
        if (!reqGameRoom.getAction().equalsIgnoreCase(ActionConstant.USER_ACTION_CREATE_ROOM) || user == null)
            return new ResponseGameRoom(ActionConstant.STATUS_ERROR, reqGameRoom.getAction(), null, null, null);
        RoomSetting rs = reqGameRoom.getAction_data();
        GameSession gs = new GameSession(user, rs.getRoom_name() , rs.getPlayers_count(), rs.getRound_count(), rs.getRound_time());
        RoomsStorage.getInstance().addRoom(gs);
        List<GameSession> action_data = new ArrayList<>();
        action_data.add(gs);

        return new ResponseGameRoom(
                ActionConstant.STATUS_SUCCESS,
                ActionConstant.USER_ACTION_CREATE_ROOM,
                action_data,
                RoomsStorage.getInstance().getRoms(),
                RoomsStorage.getInstance().getUnfinishedRoomToUser(user));
    }

    @RequestMapping(value = "/jointoroom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseGameRoom joinToRoom(@RequestBody Join reqJoin) { // связать с классами Мишы & доделать
        User user = UserDAO.getUser(reqJoin.getLogin());
        if (user == null) return null;//прописать ошибку

        return new ResponseGameRoom(
                ActionConstant.STATUS_SUCCESS,
                ActionConstant.USER_ACTION_JOIN_TO_ROOM,
                null,
                RoomsStorage.getInstance().getRoms(),
                RoomsStorage.getInstance().getUnfinishedRoomToUser(user));
    }

    public void getPlayerStats() {

    }

}
