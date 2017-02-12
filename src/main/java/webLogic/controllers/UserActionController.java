package webLogic.controllers;

import dao.UserDAO;
import model.User;
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
import webLogic.Json2Object.simpleObjects.Room;
import webLogic.Json2Object.simpleObjects.RoomSetting;
import webLogic.Json2Object.simpleObjects.Rooms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserActionController {

    @RequestMapping(value = "/getrooms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseGameRoom getrooms(String login){  // метод можно вынести в аутентификацию, чтобы при входе сразу подгружалось его сотояние

        List<Room> rooms = Rooms.getInstance().getRoms();
        List<Room> unfinished_rooms = new ArrayList<>(); // getRoomsToUser(user.getLogin()); добавить в dao
        for(Room cur: Rooms.getInstance().getRoomsToUser(login) ){
            if (cur.getRoom_status().equalsIgnoreCase(ActionConstant.ROOM_STATUS_UNFINISHED)) unfinished_rooms.add(cur);
        }
        return new ResponseGameRoom(ActionConstant.STATUS_SUCCESS,ActionConstant.USER_ACTION_GET_ROOM_LIST,null,rooms,unfinished_rooms);

    }

    @RequestMapping(value = "/createroom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseGameRoom createRoom(@RequestBody RequestGameRoom reqGameRoom) throws SQLException {

            if (!reqGameRoom.getAction().equalsIgnoreCase(ActionConstant.USER_ACTION_CREATE_ROOM))
                return new ResponseGameRoom(ActionConstant.STATUS_ERROR,reqGameRoom.getAction(),null,null,null);

            RoomSetting rs =reqGameRoom.getAction_data();
            Room room = new Room(
                    rs.getRoom_id(),
                    rs.getRoom_name(),
                    ActionConstant.ROOM_STATUS_AVAILABLE,
                    rs.getPlayers_count(),
                    rs.getRound_count(),
                    rs.getRound_time(),
                    reqGameRoom.getLogin(),
                    new String[]{reqGameRoom.getLogin()});

            List<Room> action_data = new ArrayList<>();
            action_data.add(room);
            List<Room> rooms = Rooms.getInstance().getRoms();
            List<Room> unfinished_rooms = new ArrayList<>();
            for(Room cur: Rooms.getInstance().getRoomsToUser(reqGameRoom.getLogin()) ){
                if (cur.getRoom_status().equalsIgnoreCase(ActionConstant.ROOM_STATUS_UNFINISHED)) unfinished_rooms.add(cur);
            }
            return new ResponseGameRoom(ActionConstant.STATUS_SUCCESS,ActionConstant.USER_ACTION_CREATE_ROOM,action_data,rooms,unfinished_rooms);

    }

    @RequestMapping(value = "/jointoroom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseGameRoom joinToRoom(@RequestBody Join reqJoin) throws SQLException {
        String login = reqJoin.getLogin();

        List<Room> rooms = Rooms.getInstance().getRoms();
        Rooms.getInstance().addPlayerToRoom(reqJoin.getRoom_id(),login);


        return null;
    }


}
