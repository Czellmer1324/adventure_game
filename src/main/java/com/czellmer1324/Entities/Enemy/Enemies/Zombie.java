package com.czellmer1324.Entities.Enemy.Enemies;

import com.czellmer1324.Entities.Enemy.Enemy;
import com.czellmer1324.Entities.Enemy.EnemyType;

public class Zombie extends Enemy {
    public Zombie(String name) {
        int health = 30; int attackDmg = 4;
        super(name, health, attackDmg, EnemyType.ZOMBIE);
    }
}
