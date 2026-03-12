package com.czellmer1324.Entities.Player;

import com.czellmer1324.Entities.Item.Item;
import com.czellmer1324.Entities.Item.ItemType;
import com.czellmer1324.Entities.Item.Items.Armor.Armor;
import com.czellmer1324.Entities.Item.Items.Armor.ArmorType;
import com.czellmer1324.Records.DamageResult;

import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
    private final String name;
    private int maxHealth = 40;
    private int health = 40;
    private int attack = 10;
    private ArrayList<LinkedList<Item>> inventory = new ArrayList<>();
    private final int armorInventIndex = 0;
    private final int potionInventIndex = 1;
    private final int weaponInventIndex = 2;
    private Armor[] armors = new Armor[4];
    private boolean isDead = false;

    public Player(String name) {
        this.name = name;
        inventory.add(new LinkedList<>());
        inventory.add(new LinkedList<>());
        inventory.add(new LinkedList<>());
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public DamageResult takeDmg(int dmg) {
        health -= dmg;
        if (health <= 0) isDead = true;
        return new DamageResult(isDead, health);
    }

    public LinkedList<Item> getInvent(ItemType type) {
        if (type.equals(ItemType.ARMOR)) return inventory.get(armorInventIndex);
        else if (type.equals(ItemType.POTION)) return inventory.get(potionInventIndex);
        else return inventory.get(weaponInventIndex);
    }

    public void applyArmor(int index) {
        LinkedList<Item> armorInvent = inventory.get(armorInventIndex);
        Armor piece = (Armor) armorInvent.get(index);
        ArmorType type = piece.getArmorType();

        switch (type) {
            case ArmorType.HELMET -> {
                if (armors[0] != null) {
                    armorInvent.add(armors[0]);
                    maxHealth -= armors[0].getHealthIncrease();
                }

                armors[0] = piece;

                maxHealth += piece.getHealthIncrease();
                armorInvent.remove(index);
            }
            case ArmorType.CHESTPLATE -> {
                if (armors[1] != null) {
                    armorInvent.add(armors[1]);
                    maxHealth -= armors[1].getHealthIncrease();
                }

                armors[1] = piece;

                maxHealth += piece.getHealthIncrease();
                armorInvent.remove(index);
            }
            case ArmorType.LEGGINGS -> {
                if (armors[2] != null) {
                    armorInvent.add(armors[2]);
                    maxHealth -= armors[2].getHealthIncrease();
                }

                armors[2] = piece;

                maxHealth += piece.getHealthIncrease();
                armorInvent.remove(index);
            }
            case ArmorType.BOOTS -> {
                if (armors[3] != null) {
                    armorInvent.add(armors[3]);
                    maxHealth -= armors[3].getHealthIncrease();
                }

                armors[3] = piece;

                maxHealth += piece.getHealthIncrease();
                armorInvent.remove(index);
            }
        }
    }
}
