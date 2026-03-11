package com.czellmer1324.Entities.Item;

public abstract class Item {
    private final String name;
    private final String description;
    private final ItemType type;

    public Item(String name, String description, ItemType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }
}
