package com.czellmer1324.Entities.Enemy.Enemies;

import com.czellmer1324.Entities.Enemy.Enemy;
import com.czellmer1324.Entities.Enemy.EnemyType;

public class Vampire extends Enemy {
    public Vampire(String name) {
        int health = 20; int attackDmg = 5;
        super(name, health, attackDmg, EnemyType.VAMPIRE);
    }
}
