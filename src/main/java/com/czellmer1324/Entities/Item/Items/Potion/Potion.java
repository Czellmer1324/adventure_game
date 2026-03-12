package com.czellmer1324.Entities.Item.Items.Potion;

import com.czellmer1324.Entities.Item.Item;
import com.czellmer1324.Entities.Item.ItemType;

public class Potion extends Item {
    private final int healthPoints;

    public Potion(String name, String description, int healthPoints) {
        super(name, description, ItemType.POTION);
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
