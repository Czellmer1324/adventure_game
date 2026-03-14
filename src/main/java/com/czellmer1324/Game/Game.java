package com.czellmer1324.Game;

import com.czellmer1324.Entities.Player.Player;
import com.czellmer1324.Room.Room;
import com.czellmer1324.Room.RoomGraph;

public class Game {
    private final RoomGraph gameMap;
    private Room currentRoom;
    private final Player player;
    private boolean finished = false;

    public Game(String playerName) {
        gameMap = new CreateMap().createMap();
        currentRoom = gameMap.getStartingRoom();
        player = new Player(playerName);
    }


}
