package com.catcompanion;

public class Command {

    private String name;
    private String description;
    private String level;
    private Runnable action;

    public Command(String nameToSet, String descriptionToSet, String levelToSet, Runnable actionToSet) {
        name = nameToSet;
        description = descriptionToSet;
        level = levelToSet;
        action = actionToSet;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLevel() {return level;}

    public void execute() {
        action.run();
    }
}
