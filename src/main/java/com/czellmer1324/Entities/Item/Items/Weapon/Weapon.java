package com.czellmer1324.Entities.Item.Items.Weapon;

import com.czellmer1324.Entities.Item.Item;
import com.czellmer1324.Entities.Item.ItemType;

public class Weapon extends Item {
    private final int dmgIncrease;

    public Weapon(String name, String description, int dmgIncrease) {
        super(name, description, ItemType.WEAPON);
        this.dmgIncrease = dmgIncrease;
    }

    public int getDmgIncrease() {
        return dmgIncrease;
    }
}
