package com.czellmer1324.Game;

import com.czellmer1324.Entities.Enemy.Enemies.Boss;
import com.czellmer1324.Entities.Enemy.Enemies.Vampire;
import com.czellmer1324.Entities.Enemy.Enemies.Zombie;
import com.czellmer1324.Entities.Enemy.Enemy;
import com.czellmer1324.Entities.Item.Item;
import com.czellmer1324.Entities.Item.Items.Armor.Armor;
import com.czellmer1324.Entities.Item.Items.Armor.ArmorType;
import com.czellmer1324.Entities.Item.Items.Potion.Potion;
import com.czellmer1324.Entities.Item.Items.Weapon.Weapon;
import com.czellmer1324.Game.Room.Room;
import com.czellmer1324.Game.Room.RoomGraph;
import com.czellmer1324.Records.GameMapCreation;

import java.util.ArrayList;
import java.util.LinkedList;

public class CreateMap {
    public GameMapCreation createMap() {
        RoomGraph map = new RoomGraph();
        Room room1 = new Room("Beginning room", true, false, createItemsHelmet(), createEnemiesOne());
        Room room2 = new Room("Room of pain", false, false, createItemWeapon(), createEnemiesOne());
        Room room3 = new Room("Room of suffering", false, false, createItemsChestPlate(), createEnemiesTwo());
        Room room4 = new Room("Room of radiance", false, false, createItemsBoots(), createEnemiesOne());
        Room room5 = new Room("Room of defiance", false, false, createItemsLeggings(), createEnemiesTwo());
        Room room6 = new Room("Room of reckoning", false, true, createItemPotions(), createBossRoomEnemies());

        map.addRoom(room1);
        map.addRoom(room2);
        map.addRoom(room3);
        map.addRoom(room4);
        map.addRoom(room5);
        map.addRoom(room6);

        map.addConnection(room1, room2);
        map.addConnection(room2, room3);
        map.addConnection(room2, room4);
        map.addConnection(room3, room4);
        map.addConnection(room4, room5);
        map.addConnection(room5, room6);

        return new GameMapCreation(room1, map);
    }

    private LinkedList<Enemy> createEnemiesOne() {
        LinkedList<Enemy> enemies = new LinkedList<>();
        enemies.add(new Zombie("Zombie1"));
        enemies.add(new Vampire("Vampire1"));
        enemies.add(new Zombie("Zombie2"));
        return enemies;
    }

    private LinkedList<Enemy> createEnemiesTwo() {
        LinkedList<Enemy> enemies = new LinkedList<>();
        enemies.add(new Zombie("Zombie1"));
        enemies.add(new Vampire("Vampire1"));
        enemies.add(new Zombie("Zombie2"));
        enemies.add(new Zombie("Zombie3"));
        enemies.add(new Vampire("Vampire2"));
        return enemies;
    }

    private LinkedList<Enemy> createBossRoomEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();
        enemies.add(new Zombie("Zombie1"));
        enemies.add(new Vampire("Vampire1"));
        enemies.add(new Boss("Boss Man"));
        return enemies;
    }

    private LinkedList<Item> createItemsHelmet() {
        LinkedList<Item> items = new LinkedList<>();
        items.add(new Armor("Helmet of Truth", "Protect your noggin", ArmorType.HELMET, 10));
        items.add(new Potion("Potion of healing", "Comes in handy when your health is low", 10));
        return items;
    }

    private LinkedList<Item> createItemsChestPlate() {
        LinkedList<Item> items = new LinkedList<>();
        items.add(new Armor("Chestplate of Truth", "Makes you look cool while protecting your lungs", ArmorType.CHESTPLATE, 5));
        items.add(new Potion("Potion of healing", "Comes in handy when your health is low", 10));
        items.add(new Potion("Potion of healing", "Comes in handy when your health is low", 10));
        return items;
    }

    private LinkedList<Item> createItemsLeggings() {
        LinkedList<Item> items = new LinkedList<>();
        items.add(new Armor("Leggings of Truth", "Does this make my butt look big?", ArmorType.LEGGINGS, 4));
        items.add(new Potion("Potion of healing", "Comes in handy when your health is low", 10));
        items.add(new Potion("Potion of healing", "Comes in handy when your health is low", 10));
        return items;
    }

    private LinkedList<Item> createItemsBoots() {
        LinkedList<Item> items = new LinkedList<>();
        items.add(new Armor("Boots of Truth", "Lemon peppa steppa's", ArmorType.BOOTS, 5));
        items.add(new Potion("Potion of healing", "Comes in handy when your health is low", 20));
        return items;
    }

    private LinkedList<Item> createItemPotions() {
        LinkedList<Item> items = new LinkedList<>();
        items.add(new Potion("Potion of healing", "Comes in handy when your health is low", 20));
        items.add(new Potion("Potion of healing", "Comes in handy when your health is low", 20));
        return items;
    }

    private LinkedList<Item> createItemWeapon() {
        LinkedList<Item> items = new LinkedList<>();
        items.add(new Weapon("Axe of hacking", "Hack through your enemies with ease", 15));
        items.add(new Potion("Potion of healing", "Comes in handy when your health is low", 20));
        items.add(new Potion("Potion of healing", "Comes in handy when your health is low", 20));
        return items;
    }
}
