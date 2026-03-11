package com.czellmer1324.Entities.Enemy.Enemies;

import com.czellmer1324.Entities.Enemy.Enemy;
import com.czellmer1324.Entities.Enemy.EnemyType;

public class Boss extends Enemy {
    public Boss(String name) {
        int health = 100; int attackDmg = 11;
        super(name, health, attackDmg, EnemyType.BOSS);
    }
}
