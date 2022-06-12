package com.catcompanion;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.io.File;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Objects;

public class CatCommands {
    public static void meow(Cat cat) {

        ServiceCommands.println(Data.output,
                cat.getName() + " says:");
        ServiceCommands.println(Data.output,
                "\tMeow");

        Media sound = new Media(Objects
                .requireNonNull(CatCompanionApp.class
                        .getClassLoader()
                        .getResource("com/catcompanion/meow.mp3"))
                .toExternalForm());
        Data.meowPlayer = new MediaPlayer(sound);
        Data.meowPlayer.setVolume(0.5);
        Data.meowPlayer.play();
    }
    public static void playWith(Cat cat) {

        cat.setMood(Moods.happy);
        cat.setHunger(HungerLvls.Lvls[HungerLvls.findIndex(HungerLvls.Lvls, cat.getHunger()) - 1]);

        ServiceCommands.println(Data.output,
                "You play with " + cat.getName());
        ServiceCommands.println(Data.output,
                cat.getName() + " mood is now " + cat.getMood());
        ServiceCommands.println(Data.output,
                cat.getName() + " hunger bar is now " + cat.getHunger());
    }

    public static void feedTheCat(Cat cat) {
        // add option to choose food

        cat.setHunger(HungerLvls.Lvls[5]);
        cat.setMood(Moods.normal);

        ServiceCommands.println(Data.output,
                "You feed " + cat.getName() + " with best catfood!");
        ServiceCommands.println(Data.output,
                cat.getName() + " mood is now " + cat.getMood() +
                "\nAnd its hunger is " + cat.getHunger());
    }

    public static void checkHunger(Cat cat) {

        if(cat.getHunger().equals(HungerLvls.Lvls[0])) {
            cat.setMood(Moods.hungry);
            //System.out.println("\n" + cat.GetName() + " mood is now " + cat.GetMood());

            ServiceCommands.println(Data.output,
                    cat.getName() +
                    " is too hungry to do anything :(\n" +
                    "Please feed " + cat.getName());
        }
    }

    public static void catStatus(Cat cat) {

        ServiceCommands.println(Data.output,
                MessageFormat.format(
                "Your cat: {0}, {1} yrs, {2}, {3}\n" +
                        "{0} catty mood: {4}\n" +
                        "{0} hunger lvl: {5}",
                cat.getName(), cat.getAge(), cat.getGender(), cat.getColor(), cat.getMood(), cat.getHunger()));
    }

    public static void catMiniStatus(Cat cat) {
        try {
            Data.statusLabel.setStyle("-fx-text-fill: " + cat.getColor() + ";");
        } catch (Exception e) {
            Data.statusLabel.setStyle("-fx-text-fill: black;");
        }
        Data.statusLabel.setText(
                MessageFormat.format(
                "Your Cat:\n" +
                        "{0}, {1} yrs\n" +
                        "{2}, {3}\n" +
                        "{4}, {5}\n",
                cat.getName(), cat.getAge(), cat.getGender(), cat.getColor(), cat.getMood(), cat.getHunger()));

        ImageView imgV = new ImageView(new Image("com/catcompanion/catIconBase.png"));
        imgV.setFitHeight(Data.imageView.getFitHeight());
        imgV.setFitWidth(Data.imageView.getFitWidth());

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
    }
}
