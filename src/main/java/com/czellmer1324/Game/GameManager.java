package com.czellmer1324.Game;

import com.czellmer1324.Entities.Enemy.Enemy;
import com.czellmer1324.Entities.Enemy.EnemyType;
import com.czellmer1324.Entities.Item.Item;
import com.czellmer1324.Entities.Item.ItemType;
import com.czellmer1324.Entities.Player.Player;
import com.czellmer1324.Game.Room.Room;
import com.czellmer1324.Records.ChoiceValidation;
import com.czellmer1324.Records.DamageResult;
import com.czellmer1324.Records.PlayerInfo;
import com.czellmer1324.Records.PotionResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class GameManager {
    private final Game curGame;
    private Room curRoom;
    private final Player player;
    private final GameUtils utils = new GameUtils();
    private final Scanner sc;

    public GameManager(String name, Scanner sc) {
        curGame = new Game(name);
        curRoom = curGame.getStartingRoom();
        player = new Player(name);
        this.sc = sc;
    }

    public void startGame() {
        utils.printRules(player.getName());
        //Waits for player to finish reading rules before continuing.
        sc.nextLine();

        do {
            IO.println("\nYou are currently in the " + curRoom.getRoomName());
            utils.printOptions();

            ChoiceValidation choiceValidation = utils.validateMainOptionChoice(sc.nextLine());
            while (!choiceValidation.valid()) {
                IO.print("Please enter a valid choice: ");
                choiceValidation = utils.validateMainOptionChoice(sc.nextLine());
            }

            int choice = choiceValidation.choice();
            chooseOption(choice);

        } while (!curGame.gameFinished());

        //TODO: Add logic for after the game has finished. Method call to print weather they won or lost.
    }

    private int getOption(int low, int high) {
        ChoiceValidation valid = utils.validateOption(sc.nextLine(), low, high);
        while (!valid.valid()) {
            IO.print("Please enter a valid choice: ");
            valid = utils.validateOption(sc.nextLine(), low, high);
        }

        // reduce by one to be used as index
        return valid.choice() - 1;
    }

    private void chooseOption(int choice) {
        switch (choice) {
            case 1 -> viewItemsRoom();
            case 2 -> enemiesLeft();
            case 3 -> combat();
            case 4 -> viewStats();
            case 5 -> viewInventory();
            case 6 -> moveRoom();
            case 7 -> quitGame();
        }
    }

    private void viewItemsRoom() {
        LinkedList<Item> curItems = curRoom.getItems();
        if (curItems.isEmpty()) {
            IO.println("\nThere are no items available in the room.");
        } else {
            IO.println("\nItems available: ");
            for (int i = 0; i < curItems.size(); i++) {
                IO.println(String.format("%d: %s", i + 1, curItems.get(i).getName()));
            }

            IO.print("Would you like to pick up an item? (Yes/No): ");
            String pickUp = sc.nextLine().toLowerCase();
            if (pickUp.equals("yes")) {
                IO.print("Enter the number of the item you would like to pick up: ");
                int choice = getOption(1, curItems.size());

                pickUpItem(choice);
            }
        }
    }

    private void pickUpItem(int selection) {
        Item item = curRoom.pickupItem(selection);
        player.pickUpItem(item);
        IO.println(item.getName() + " has been added to your inventory");
    }

    private void enemiesLeft() {
        IO.println("\n There are currently " + curRoom.getNumEnemies() + " enemies left in the room.");
    }

    private void combat() {
        if (curRoom.enemiesDefeated()) {
            IO.println("There are no enemies remaining in this room!");
        } else {
            Enemy curEnemy = curRoom.getNextEnemy();
            IO.println("\nYou are now entering battle against " + curEnemy.getName());
            IO.println("They have " + curEnemy.getHealth() + " health to start. Good luck in battle!");
            viewStats();
            IO.println("\nYou will make the first move!");

            boolean combatOver = false;
            while (!combatOver) {
                utils.printCombatOptions();
                int choice = getOption(1, 3);

                switch (choice) {
                    case 0 -> {
                       DamageResult playerAttackResult = curEnemy.takeDmg(player.getAttack());
                       if (playerAttackResult.isDead()) {
                           if (curEnemy.getType().equals(EnemyType.BOSS)) {
                               curGame.setFinished();
                           }

                           IO.println("You have slaughtered the enemy!");
                           curRoom.enemyDefeated(curEnemy);
                           combatOver = true;
                           IO.print("Great job in combat " + player.getName() + "! Press enter to continue");
                           sc.nextLine();
                       } else {
                           IO.println("Good attack! Your enemies health is now: " + playerAttackResult.health());
                       }
                    }
                    case 1 -> {
                        LinkedList<Item> potions = player.getInvent(1);
                        if (potions.isEmpty()) {
                            IO.println("Sorry you do not have any potions to use.");
                        } else {
                            PotionResult result = player.usePotion(0);
                            IO.println("Your health is now: " + result.health());
                        }
                    }
                    case 2 -> {
                        IO.println("You are now exiting this combat scenario.");
                        combatOver = true;
                    }
                }

                if (!combatOver) {
                    IO.println("\nNow its your enemies turn to attack");
                    DamageResult result = player.takeDmg(curEnemy.attack());
                    if (result.isDead()) {
                        IO.println("You have been killed.");
                        curGame.setFinished();
                        combatOver = true;
                    } else {
                        IO.println("Wow you tanked that hit! Your current health is: " + player.getHealth() + "/" + player.getMaxHealth());
                    }
                }
            }
        }
    }

    private void viewStats() {
        IO.println("\n" + player.getName() + "'s current stats: ");
        PlayerInfo info = player.getCurrentStats();
        IO.println("Current Health: " + info.health());
        IO.println("Max Health: " + info.maxHealth());
        IO.println("Current attack: " + info.attack());
        IO.println("Helmet: " + ((info.armor()[0] != null) ? info.armor()[0].getName() : "No helmet"));
        IO.println("Chestplate: " + ((info.armor()[1] != null) ? info.armor()[1].getName() : "No chestplate"));
        IO.println("Leggings: " + ((info.armor()[2] != null) ? info.armor()[2].getName() : "No leggings"));
        IO.println("Boots: " + ((info.armor()[3] != null) ? info.armor()[3].getName() : "No boots"));
        IO.println("Weapon: " + ((info.weapon() != null) ? info.weapon().getName() : "No weapon"));
        IO.print("Press enter to continue");
        sc.nextLine();
    }

    private void viewInventory() {
        IO.println("Which inventory would you like to view?");
        IO.println("1. Armor\n2. Potions\n3. Weapons");
        IO.print("Your choice: ");
        int choice = getOption(1, 3);

        LinkedList<Item> items = player.getInvent(choice);
        if (items.isEmpty()) {
            IO.println("You don't have any items of this kind in your inventory.");
        } else {
            ItemType type = items.getFirst().getType();
            IO.println(String.format("\n%s's current inventory.", player.getName()));
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                IO.println(String.format("%d: %s", i + 1, item.getName()));
                IO.println("\tDescription: " + item.getDescription());
            }

            IO.print(String.format("Would you like to %s an item? (Yes/No): ", (type.equals(ItemType.ARMOR) || type.equals(ItemType.WEAPON) ? "equip" : "use")));
            if (sc.nextLine().equalsIgnoreCase("yes")) {
                useItem(items, type);
            }
        }
    }

    private void useItem(LinkedList<Item> items, ItemType type) {
        IO.print("Enter the number of the item you would like to use: ");
        int choice = getOption(1, items.size());

        if (type.equals(ItemType.ARMOR)) player.applyArmor(choice);
        else if (type.equals(ItemType.WEAPON)) player.applyWeapon(choice);
        else {
            PotionResult result = player.usePotion(choice);
            if (result.used()) {
                IO.println("You have been healed, your current health is " + result.health());
            } else {
                IO.println("You are already at full health, the potion has not been used.");
            }
        }
    }

    private void moveRoom() {

    }

    private void quitGame() {
        IO.println("Are you sure you would like to quit the game? Your progress will not be saved.");
        IO.print("Type YES to confirm: ");
        String quit = sc.nextLine();
        if (quit.equals("YES")) {
            IO.println("Thank you for playing, " + player.getName()+ "!");
            System.exit(0);
        }
    }
}
