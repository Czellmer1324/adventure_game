package com.czellmer1324.Game.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomGraph {
    private final HashMap<String, ArrayList<Room>> adjRooms = new HashMap<>();

    public void addRoom(Room room) {
        adjRooms.putIfAbsent(room.getRoomName(), new ArrayList<>());
    }

    public void addConnection(Room room1, Room room2) {
        adjRooms.get(room1.getRoomName()).add(room2);
        adjRooms.get(room2.getRoomName()).add(room1);
    }

    public ArrayList<Room> getAdjacentRooms(Room room) {
        return adjRooms.get(room.getRoomName());
    }
}
