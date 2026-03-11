package com.czellmer1324.Entities.Enemy;

public abstract class Enemy {
    private EnemyType type;
    private int health;
    private int attackDmg;
    private boolean dead = false;
    private String name;

    public Enemy(String name) {}

    public int attack() {
        return attackDmg;
    }

    public boolean takeDmg(int dmg) {
        health -= dmg;
        if (health <= 0) dead = true;
        return dead;
    }

    public EnemyType getType() {
        return type;
    }
}
