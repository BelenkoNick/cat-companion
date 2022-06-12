package com.catcompanion;

import javafx.scene.control.TextArea;

import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceCommands {

    // Simple custom print method
    public static void println(TextArea output, String line) {
        output.appendText(line + "\n");
    }

    // Checks input commands and executes them
    public static void onInput(Map<String, Command> commands, String line)  {
        if (!commands.containsKey(line.toLowerCase(Locale.ROOT))) {
            println(Data.output,
                    "Command " + line + " not found\n" +
                    "Please try again :^)");
            return;
        }
        commands.get(line).execute();
    }

    //Returns a list of available commands
    public static void getCommands() {
        println(Data.output, "Cat Commands:");
        for (Map.Entry<String, Command> entry : Data.commands.entrySet()) {
            if (entry.getValue().getLevel().equals("cat")) {
                println(Data.output, entry.getKey() + " - " + entry.getValue().getDescription());
            }
        }
        println(Data.output, "\nService Commands:");
        for (Map.Entry<String, Command> entry : Data.commands.entrySet()) {
            if (entry.getValue().getLevel().equals("service")) {
                println(Data.output, entry.getKey() + " - " + entry.getValue().getDescription());
            }
        }
    }
    //Provides info and help on the game
    public static void help() {

    }

    // Closes application
    public static void closeApp() {
        new Timer().schedule(new TimerTask() {
            public void run () { System.exit(0); }
        }, 7000);
        println(Data.output,"// Made in collaboration with\n" +
                "and inspired by my girlfriend!\n" +
                "/*\n\tBig thanks to our cats:\n" +
                "\tJoja, Trisha and Molly\n" +
                "\tfor being such cool kitties\n" +
                "\tto made a game of them!\n*\\");
    }
}
