package com.czellmer1324.Game;

import com.czellmer1324.Entities.Enemy.Enemy;
import com.czellmer1324.Entities.Item.Item;

import java.util.ArrayList;

public class Room {
    private final String roomName;
    private final Boolean startingRoom;
    private final Boolean endingRoom;
    private final ArrayList<Item> items;
    private final ArrayList<Enemy> enemies;
    private Boolean allEnemiesDefeated = false;
    private final ArrayList<Room> adjoiningRooms = new ArrayList<>();

    public Room(String roomName, boolean startingRoom, boolean endingRoom, ArrayList<Item> items, ArrayList<Enemy> enemies) {
        this.roomName = roomName; this.startingRoom = startingRoom; this.endingRoom = endingRoom; this.items = items;
        this.enemies = enemies;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void enemyDefeated(Enemy enemy) {
        enemies.remove(enemy);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item pickupItem(int index) {
        return items.get(index);
    }

    public void dropItem(Item item) {
        items.add(item);
    }
}
