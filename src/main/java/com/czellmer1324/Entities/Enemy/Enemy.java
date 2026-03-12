package com.czellmer1324.Entities.Enemy;

import com.czellmer1324.Records.DamageResult;

public abstract class Enemy {
    private EnemyType type;
    private int health;
    private int attackDmg;
    private boolean dead = false;
    private String name;

    public Enemy(String name, int health, int attackDmg, EnemyType type) {
        this.name = name;
        this.health = health;
        this.attackDmg = attackDmg;
        this.type = type;
    }

    public int attack() {
        return attackDmg;
    }

    public DamageResult takeDmg(int dmg) {
        health -= dmg;
        if (health <= 0) dead = true;
        return new DamageResult(dead, health);
    }

    public EnemyType getType() {
        return type;
    }
}
