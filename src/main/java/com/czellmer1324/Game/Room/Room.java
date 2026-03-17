package com.czellmer1324.Game.Room;

import com.czellmer1324.Entities.Enemy.Enemy;
import com.czellmer1324.Entities.Item.Item;

import java.util.ArrayList;
import java.util.Objects;

public class Room {
    private final String roomName;
    private final Boolean startingRoom;
    private final Boolean endingRoom;
    private final ArrayList<Item> items;
    private final ArrayList<Enemy> enemies;
    private Boolean allEnemiesDefeated = false;

    public Room(String roomName, boolean startingRoom, boolean endingRoom, ArrayList<Item> items, ArrayList<Enemy> enemies) {
        this.roomName = roomName; this.startingRoom = startingRoom; this.endingRoom = endingRoom; this.items = items;
        this.enemies = enemies;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public boolean anyEnemies() {
        return allEnemiesDefeated;
    }

    public boolean enemyDefeated(Enemy enemy) {
        enemies.remove(enemy);
        if (enemies.isEmpty()) {
            allEnemiesDefeated = true;
        }

        return allEnemiesDefeated;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean startingRoom() {
        return startingRoom;
    }

    public boolean bossRoom() {
        return endingRoom;
    }

    public Item pickupItem(int index) {
        return items.get(index);
    }

    public void dropItem(Item item) {
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomName, room.roomName) && Objects.equals(startingRoom, room.startingRoom) && Objects.equals(endingRoom, room.endingRoom) && Objects.equals(items, room.items) && Objects.equals(enemies, room.enemies) && Objects.equals(allEnemiesDefeated, room.allEnemiesDefeated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName, startingRoom, endingRoom, items, enemies, allEnemiesDefeated);
    }
}
