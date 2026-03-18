package com.czellmer1324.Game;

import com.czellmer1324.Entities.Player.Player;
import com.czellmer1324.Game.Room.Room;
import com.czellmer1324.Game.Room.RoomGraph;
import com.czellmer1324.Records.GameMapCreation;

import java.util.ArrayList;

public class Game {
    private final RoomGraph gameMap;
    private final Room startingRoom;
    private final Player player;
    private boolean finished = false;

    public Game(String playerName) {
        GameMapCreation mapCreation = new CreateMap().createMap();
        gameMap = mapCreation.map();
        startingRoom = mapCreation.startingRoom();
        player = new Player(playerName);
    }

    public Room getStartingRoom() {
        return startingRoom;
    }

    public Player getPlayer() {
        return player;
    }

    public void setFinished() {
        finished = true;
    }

    public boolean gameFinished() {
        return finished;
    }

    public ArrayList<Room> getAdjacentRooms(Room room) {
        return gameMap.getAdjacentRooms(room);
    }

}
