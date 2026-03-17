package com.czellmer1324.Game.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomGraph {
    private final HashMap<Room, ArrayList<Room>> adjRooms = new HashMap<>();

    public void addRoom(Room room) {
        adjRooms.putIfAbsent(room, new ArrayList<>());
    }

    public void addConnection(Room room1, Room room2) {
        adjRooms.get(room1).add(room2);
        adjRooms.get(room2).add(room1);
    }

    public ArrayList<Room> getAdjacentRooms(Room room) {
        return adjRooms.get(room);
    }

    public Room getStartingRoom() {
        for (Room room : adjRooms.keySet()) {
            if (room.startingRoom()) return room;
        }

        return null;
    }
}
