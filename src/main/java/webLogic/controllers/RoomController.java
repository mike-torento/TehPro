package webLogic.controllers;

import dao.UserDAO;
import model.GameSession;
import model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webLogic.Json2Object.ActionConstant;
import webLogic.Json2Object.response.ResponseGameRoom;
import webLogic.Json2Object.RoomsStorage;
import webLogic.Json2Object.simpleObjects.Join;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {

    @RequestMapping(value = "/getrooms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseGameRoom getRooms(@RequestBody User reqUser) { // проверить как работает
        User user = UserDAO.getUser(reqUser.getLogin());
        if (user==null) return null; // прописать ошибку
        List<GameSession> rooms = RoomsStorage.getInstance().getRoms();
        List<GameSession> unfinished = RoomsStorage.getInstance().getUnfinishedRoomToUser(user);

        return new ResponseGameRoom(ActionConstant.STATUS_SUCCESS, null, null, rooms, unfinished);
    }

    @RequestMapping(value = "/updateroom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseGameRoom updateroom(@RequestBody Long room_id){
        for (GameSession cur: RoomsStorage.getInstance().getRoms()){
            if (Long.compare(cur.getSessionID(),room_id) == 1 ) {
                List<GameSession> action_data = new ArrayList<GameSession>();
                action_data.add(cur);
                return new ResponseGameRoom(ActionConstant.STATUS_SUCCESS,ActionConstant.ROOM_UPDATE,action_data,null,null);
            }
        }
        return null; // прописать ошибку
    }
}
