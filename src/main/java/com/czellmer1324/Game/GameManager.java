package com.czellmer1324.Game;

import com.czellmer1324.Entities.Player.Player;
import com.czellmer1324.Game.Room.Room;
import com.czellmer1324.Records.ChoiceValidation;

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
            
        }
    }
}
