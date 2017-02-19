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
}
