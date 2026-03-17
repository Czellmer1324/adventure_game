package com.czellmer1324.Entities.Player;

import com.czellmer1324.Entities.Item.Item;
import com.czellmer1324.Entities.Item.ItemType;
import com.czellmer1324.Entities.Item.Items.Armor.Armor;
import com.czellmer1324.Entities.Item.Items.Armor.ArmorType;
import com.czellmer1324.Entities.Item.Items.Potion.Potion;
import com.czellmer1324.Entities.Item.Items.Weapon.Weapon;
import com.czellmer1324.Records.DamageResult;
import com.czellmer1324.Records.PlayerInfo;
import com.czellmer1324.Records.PotionResult;

import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
    private final String name;
    private int maxHealth = 40;
    private int health = 40;
    private int attack = 10;
    private final ArrayList<LinkedList<Item>> inventory = new ArrayList<>();
    private final int armorInventIndex = 0;
    private final int potionInventIndex = 1;
    private final int weaponInventIndex = 2;
    private final Armor[] armors = new Armor[4];
    private Weapon curWeapon = null;
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

    public LinkedList<Item> getInvent(int index) {
        if (index == armorInventIndex) return inventory.get(armorInventIndex);
        else if (index == potionInventIndex) return inventory.get(potionInventIndex);
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

    public void applyWeapon(int index) {
        LinkedList<Item> weaponInvent = inventory.get(weaponInventIndex);
        Weapon weapon = (Weapon) weaponInvent.get(index);

        if (curWeapon != null) {
            attack -= curWeapon.getDmgIncrease();
            weaponInvent.add(curWeapon);
        }

        curWeapon = weapon;
        weaponInvent.remove(weapon);
        attack += weapon.getDmgIncrease();
    }

    public PotionResult usePotion(int index) {
        LinkedList<Item> potionInvent = inventory.get(potionInventIndex);
        Potion potion = (Potion) potionInvent.get(index);

        PotionResult result = usePotion(potion);
        if (result.used()) {
            potionInvent.remove(potion);
        }

        return result;
    }

    private PotionResult usePotion(Potion potion) {
        int healAmount = potion.getHealthPoints();
        int availableToHeal = maxHealth - health;

        if (availableToHeal == 0) {
            return new PotionResult(health, false);
        } else if (healAmount >= availableToHeal) {
            health = maxHealth;
            return new PotionResult(health, true);
        } else {
            health += healAmount;
            return new PotionResult(health, true);
        }
    }

    public PlayerInfo getCurrentStats() {
        return new PlayerInfo(health, maxHealth, attack, armors, curWeapon);
    }

    public void dropItem(Item item) {
        ItemType type = item.getType();
        if (type.equals(ItemType.ARMOR)) {
            inventory.get(armorInventIndex).remove(item);
        } else if (type.equals(ItemType.WEAPON)) {
            inventory.get(weaponInventIndex).remove(item);
        } else {
            inventory.get(potionInventIndex).remove(item);
        }
    }

    public void pickUpItem(Item item) {
        ItemType type = item.getType();
        if (type.equals(ItemType.ARMOR)) {
            inventory.get(armorInventIndex).add(item);
        } else if (type.equals(ItemType.WEAPON)) {
            inventory.get(weaponInventIndex).add(item);
        } else {
            inventory.get(potionInventIndex).add(item);
        }
    }
}
