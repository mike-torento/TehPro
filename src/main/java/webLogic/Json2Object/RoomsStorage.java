package webLogic.Json2Object;

import dao.SessionDAO;
import model.GameSession;
import model.User;
import webLogic.Json2Object.simpleObjects.Room;

import java.util.ArrayList;
import java.util.List;


public class RoomsStorage {

    private static volatile RoomsStorage instance;
    private List<GameSession> roms;

    private RoomsStorage() {
        roms = SessionDAO.getAllSessions();
    }

    public static RoomsStorage getInstance() {
        RoomsStorage localInstance = instance;
        if (localInstance == null) {
            synchronized (RoomsStorage.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RoomsStorage();
                }
            }
        }
        return localInstance;
    }

    public List<GameSession> getRoms() {
        return roms;
    }

    //--------------------------------------------------
    public void addRoom(GameSession room) {
        roms.add(room);
    }

    //
//    public List<GameSession> getRoomsToUser(User login) {
//        List<Room> userRooms = new ArrayList<>();
//        for (GameSession cur : roms) {
//            if (cur.getRoom_creator().equalsIgnoreCase(login)) userRooms.add(cur);
//        }
//        if (!userRooms.isEmpty()) return userRooms;
//        else return null;
//    }
    public GameSession getRoomForID(long ID) {
        for (GameSession cur : roms) {
            if (cur.getSessionID() == ID) return cur;
        }
        return null;
    }

    public List<GameSession> getUnfinishedRoomToUser(User user) {
        List<GameSession> unfinished = new ArrayList<GameSession>();
        for (GameSession cur : roms) {
            if (cur.getState()==1 && cur.getUserList().contains(user))
                unfinished.add(cur);
        }
        if (!unfinished.isEmpty())
            return unfinished;
        return null;
    }

}
