package com.catcompanion;

public class Cat {

    // Fields
    private String name;
    private int age;
    private String gender;
    private String color;
    private String mood;
    private String hunger;

    // Constructors
    public Cat() {
        mood = Moods.normal;
        hunger = HungerLvls.Lvls[5];
    }

    // Setters
    public void setName(String nameToSet) {name = nameToSet;}
    public void setAge(int ageToSet) {age = ageToSet;}
    public void setGender(String genderToSet) {gender = genderToSet;}
    public void setColor(String colorToSet) {color = colorToSet;}
    public void setMood(String moodToSet) {mood = moodToSet;}
    public void setHunger(String hungerToSet) {hunger = hungerToSet;}

    // Getters
    public String getName() {return name;}
    public int getAge() {return age;}
    public String getGender() {return gender;}
    public String getColor() {return color;}
    public String getMood() {return mood;}
    public String getHunger() {return hunger;}
}
