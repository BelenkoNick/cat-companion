package com.catcompanion;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CatCreation {
    public static int createCat(ArrayList<Cat> cats, Cat tmpCat, String text) {

        if (tmpCat.getName() == null) {

            tmpCat.setName(text);
            ServiceCommands.println(Data.crOutput,
                    "Set " + tmpCat.getName() + " age:");

        } else if (tmpCat.getAge() == 0) {

            try {
                tmpCat.setAge(Integer.parseInt(text));
                ServiceCommands.println(Data.crOutput,
                        "Set " + tmpCat.getName() + " gender: F or M");
            } catch (NumberFormatException nfe) {
                ServiceCommands.println(Data.crOutput, "That's not a number!");
                ServiceCommands.println(Data.crOutput,
                        "Set " + tmpCat.getName() + " age:");
            }

        } else if (tmpCat.getGender() == null) {

            if (!text.equalsIgnoreCase("F") &&
                    !text.equalsIgnoreCase("M")) {
                ServiceCommands.println(Data.crOutput,"That's not F or M!");
            } else {
                tmpCat.setGender(text.toUpperCase(Locale.ROOT));
                ServiceCommands.println(Data.crOutput,
                        "Set " + tmpCat.getName() + " color:");
            }

        } else if (tmpCat.getColor() == null) {

            tmpCat.setColor(text);

            cats.add(tmpCat);
            return cats.indexOf(tmpCat);

        }
        return -1;
    }
    public static void chooseCat(ArrayList<Cat> cats) {

        ServiceCommands.println(Data.chOutput,
                "Choose Cat Id:");
        for (int i = 1; i < cats.size(); i++){
            ServiceCommands.println(Data.chOutput,
                    MessageFormat.format("{0} - {1}",
                            cats.indexOf(cats.get(i)), cats.get(i).getName()));
        }
    }
}
