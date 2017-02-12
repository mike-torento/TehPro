package webLogic.Json2Object.simpleObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PiuPiu on 12.02.2017.
 */
public class Rooms {

    private static volatile Rooms instance;
    private  List<Room> roms;

    private Rooms(){
        roms = new ArrayList<>();
    }

    public static Rooms getInstance() {
        Rooms localInstance = instance;
        if (localInstance == null) {
            synchronized (Rooms.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Rooms();
                }
            }
        }
        return localInstance;
    }

    public List<Room> getRoms() {
        return roms;
    }

    public void setRoms(List<Room> roms) {
        this.roms = roms;
    }
    //--------------------------------------------------
    public void addRoom(Room room){
        roms.add(room);
    }
    public List<Room> getRoomsToUser(String login){
        List<Room> userRooms = new ArrayList<>();
        for (Room cur: roms){
            if (cur.getRoom_creator().equalsIgnoreCase(login)) userRooms.add(cur);
        }
        if (!userRooms.isEmpty()) return userRooms;
        else return null;
    }
    public Room getRoomForID(int ID){
        for (Room cur: roms){
            if (cur.getID()==ID) return cur;
        }
        return null;
    }

    public void addPlayerToRoom(int room_id, String login){
        for (Room cur: roms){
            if (cur.getID()==room_id){
                String[] players = cur.getPlayers();
                for (int i = 0; i>players.length; i++){
                    if (players[i]==null) players[i] = login;
                }
            }
        }
    }

    //  |


}
