package com.catcompanion;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.text.MessageFormat;
import java.util.Objects;

public class Cat {

    // Fields
    private String name;
    private int age;
    private String gender;
    private String color;
    private String mood;
    private String hunger;
    private String thirst;

    // Constructors
    public Cat() {
        mood = Moods.normal;
        hunger = HungerLvls.Lvls[5];
        thirst = HungerLvls.Lvls[5];
    }

    // Setters
    public void setName(String nameToSet) {name = nameToSet;}
    public void setAge(int ageToSet) {age = ageToSet;}
    public void setGender(String genderToSet) {gender = genderToSet;}
    public void setColor(String colorToSet) {color = colorToSet;}
    public void setMood(String moodToSet) {mood = moodToSet;}
    public void setHunger(String hungerToSet) {hunger = hungerToSet;}
    public void setThirst(String thirstToSet) {thirst = thirstToSet;}

    // Getters
    public String getName() {return name;}
    public int getAge() {return age;}
    public String getGender() {return gender;}
    public String getColor() {return color;}
    public String getMood() {return mood;}
    public String getHunger() {return hunger;}
    public String getThirst() {return thirst;}

    public void meow() {

        ServiceCommands.println(Data.output,
                getName() + " says:");
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
    public void playWith() {

        setMood(Moods.happy);
        setHunger(HungerLvls.Lvls[HungerLvls.findIndex(HungerLvls.Lvls, getHunger()) - 1]);

        ServiceCommands.println(Data.output,
                "You play with " + getName());
        ServiceCommands.println(Data.output,
                getName() + " mood is now " + getMood());
        ServiceCommands.println(Data.output,
                getName() + " hunger bar is now " + getHunger());
    }

    public void feedTheCat() {
        // add option to choose food

        setHunger(HungerLvls.Lvls[5]);
        setThirst(HungerLvls.Lvls[5]);
        setMood(Moods.normal);

        ServiceCommands.println(Data.output,
                "You feed " + getName() + " with best catfood!");
        ServiceCommands.println(Data.output,
                getName() + " mood is now " + getMood() +
                        "\nAnd its hunger is " + getHunger());
    }

    public boolean checkHunger() {
        return getHunger().equals(HungerLvls.Lvls[0]);
    }

    public void catStatus() {

        ServiceCommands.println(Data.output,
                MessageFormat.format(
                        "Your cat: {0}, {1} yrs, {2}, {3}\n" +
                                "{0} catty mood: {4}\n" +
                                "{0} hunger lvl: {5}",
                        getName(), getAge(), getGender(), getColor(), getMood(), getHunger()));
    }

    public void catMiniStatus() {
        try {
            Data.statusLabel.setStyle("-fx-text-fill: " + getColor() + ";");
            Data.barsLabel.setStyle("-fx-text-fill: " + getColor() + ";");
        } catch (Exception e) {
            Data.statusLabel.setStyle("-fx-text-fill: black;");
            Data.barsLabel.setStyle("-fx-text-fill: black;");
        }
        Data.statusLabel.setText(
                MessageFormat.format(
                        "Your Cat:          \n" +
                                "{0}, {1} yrs\n" +
                                "{2}, {3}\n",
                        getName(), getAge(), getGender(), getColor()));
        Data.barsLabel.setText(
                MessageFormat.format(
                                "            {0}  \n" +
                                        "            {1}\n" +
                                        "            {2}",
                        getMood(), getHunger(), getThirst()));
        ServiceCommands.changeColor(this);
    }
}
