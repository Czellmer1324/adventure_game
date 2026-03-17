package com.czellmer1324.Game;

import com.czellmer1324.Entities.Item.Item;
import com.czellmer1324.Entities.Player.Player;
import com.czellmer1324.Game.Room.Room;
import com.czellmer1324.Records.ChoiceValidation;

import java.util.ArrayList;
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

    private void chooseOption(int choice) {
        switch (choice) {
            case 1 -> viewItemsRoom();
            case 2-> enemiesLeft();
        }
    }

    private void viewItemsRoom() {
        ArrayList<Item> curItems = curRoom.getItems();
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
                ChoiceValidation valid = utils.validateOption(sc.nextLine(), 1, curItems.size());
                while (!valid.valid()) {
                    IO.print("Please enter a valid choice: ");
                    valid = utils.validateOption(sc.nextLine(), 1, curItems.size());
                }

                pickUpItem(valid.choice() - 1);
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
}
