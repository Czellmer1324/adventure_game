package com.czellmer1324.Entities.Item.Items.Armor;

import com.czellmer1324.Entities.Item.Item;
import com.czellmer1324.Entities.Item.ItemType;

public class Armor extends Item {
    private final ArmorType armorType;
    private final int healthIncrease;

    public Armor(String name, String description, ArmorType type, int healthIncrease) {
        super(name, description, ItemType.ARMOR);
        this.armorType = type;
        this.healthIncrease = healthIncrease;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
}
