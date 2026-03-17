package com.czellmer1324.Game;

import com.czellmer1324.Records.ChoiceValidation;

class GameUtils {
    protected void printRules(String name) {
        IO.println("\nThe basic rules for this game: ");
        IO.println("1: There are different rooms within the game. Each room will contain enemies, to move to a new room\n" +
                "you must clear all enemies within that room.");
        IO.println("2: If your health drops to or below zero the game will end.");
        IO.println("3: To complete the game you must make it to the boss room and defeat the boss.");
        IO.println("4: Each room will have a chance to contain potions, armor, or weapons. These will boost stats or provide healing.");
        IO.println("Good luck on your adventure, " + name + "!");
        IO.print("Press enter to continue after reading the rules.");
    }

    protected void printOptions() {
        IO.println("What would you like to do? ");
        IO.println("1: View items in room");
        IO.println("2: See how many enemies are left");
        IO.println("3: Engage in combat");
        IO.println("4: View current player stats");
        IO.println("5: View inventory");
        IO.println("6: Move rooms");
        IO.println("7: Quit game");
        IO.print("Your choice: ");
    }

    protected ChoiceValidation validateMainOptionChoice(String s) {
        int choice;
        int highestOption = 7;
        try {
            choice = Integer.parseInt(s);
            if (choice >= 1 && choice <= highestOption) return new ChoiceValidation(choice, true);
            else return new ChoiceValidation(0, false);
        } catch (NumberFormatException e) {
            return new ChoiceValidation(0, false);
        }
    }

    protected ChoiceValidation validateOption(String s, int low, int high) {
        int choice;
        try {
            choice = Integer.parseInt(s);
            if (choice >= low && choice <= high) return new ChoiceValidation(choice, true);
            else return new ChoiceValidation(0, false);
        } catch (NumberFormatException e) {
            return new ChoiceValidation(0, false);
        }
    }
}
