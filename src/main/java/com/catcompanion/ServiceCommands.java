package com.catcompanion;

import javafx.scene.control.TextArea;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

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
        if (Data.cats.get(Data.catId).checkHunger() && !line.toLowerCase(Locale.ROOT).equals("feed")) {

            Data.cats.get(Data.catId).setMood(Moods.hungry);
            ServiceCommands.println(Data.output,
                    Data.cats.get(Data.catId).getName() +
                            " is too hungry to do anything :(\n" +
                            "Please feed " + Data.cats.get(Data.catId).getName());

        } else {
            if (!commands.containsKey(line.toLowerCase(Locale.ROOT))) {
                println(Data.output,
                        "Command " + line + " not found\n" +
                                "Please try again :^)");
                return;
            }
            commands.get(line).execute();
        }
    }

    //Returns a list of available commands
    public static void getCommands() {
        println(Data.comOutput, "Cat Commands:");
        for (Map.Entry<String, Command> entry : Data.commands.entrySet()) {
            if (entry.getValue().getLevel().equals("cat")) {
                println(Data.comOutput, entry.getKey() + " - " + entry.getValue().getDescription());
            }
        }
        println(Data.comOutput, "\nService Commands:");
        for (Map.Entry<String, Command> entry : Data.commands.entrySet()) {
            if (entry.getValue().getLevel().equals("service")) {
                println(Data.comOutput, entry.getKey() + " - " + entry.getValue().getDescription());
            }
        }
    }
    //Provides info and help on the game
    public static void help() {

    }

    public static void changeColor(Cat cat) {
        ImageView imgV = new ImageView(new Image("com/catcompanion/catIconBase.png"));
        imgV.setFitHeight(Data.imageView.getFitHeight());
        imgV.setFitWidth(Data.imageView.getFitWidth());

        ImageView imgVD = new ImageView(new Image("com/catcompanion/catDancing.gif"));
        imgVD.setFitHeight(100);
        imgVD.setFitWidth(100);

        Color color = Color.BEIGE;

        if (cat.getColor() != null) {
            color = Color.web(cat.getColor());
        }
        ColorAdjust colorAd = new ColorAdjust();
        colorAd.setHue(0.2);
        //colorAd.setBrightness(0.4);
        //colorAd.setContrast(0.4);
        colorAd.setSaturation(0.2);


        Data.imageView.setClip(imgV);
        Data.imageView.setEffect(new Blend(
                BlendMode.OVERLAY,
                colorAd,
                new ColorInput(
                        0,
                        0,
                        Data.imageView.getFitWidth(),
                        Data.imageView.getFitHeight(),
                        color
                )
        ));

        Data.imageViewDance.setClip(imgVD);
        Data.imageViewDance.setEffect(new Blend(
                BlendMode.OVERLAY,
                colorAd,
                new ColorInput(
                        0,
                        0,
                        100,
                        100,
                        color
                )
        ));
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
