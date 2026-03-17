package com.czellmer1324;

import com.czellmer1324.Game.GameManager;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        IO.println("Welcome to my text based adventure game! Let's get started!");
        IO.print("Please enter what you would like to be called for this game: ");
        String name = sc.nextLine();
        GameManager manager = new GameManager(name);
    }
}
