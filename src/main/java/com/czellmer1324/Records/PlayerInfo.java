package com.czellmer1324.Records;

import com.czellmer1324.Entities.Item.Items.Armor.Armor;
import com.czellmer1324.Entities.Item.Items.Weapon.Weapon;

public record PlayerInfo(int health, int attack, Armor[] armor, Weapon weapon) {
}
