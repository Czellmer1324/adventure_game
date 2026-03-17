package com.czellmer1324.Game;

import com.czellmer1324.Entities.Player.Player;
import com.czellmer1324.Game.Room.Room;

public class GameManager {
    Game curGame;
    Room curRoom;
    Player player;

    public GameManager(String name) {
        curGame = new Game(name);
        curRoom = curGame.getStartingRoom();
        player = new Player(name);
    }
}
