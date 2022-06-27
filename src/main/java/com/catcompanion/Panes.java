package com.catcompanion;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Panes {

    public static void creationPane() {
        VBox root = new VBox(15, Data.crOutput, Data.crInput);

        // Basic Fields and Areas rules
        Data.crOutput.setPrefHeight(250);
        Data.crOutput.setFont(Font.font(16));
        Data.crOutput.setEditable(false);
        Data.crOutput.setFocusTraversable(false);

        root.setPadding(new Insets(15));
        root.setPrefSize(250, 300);
        root.setStyle("-fx-background-color: Beige;");

        Stage stage = new Stage();
        stage.setTitle("Create");
        stage.getIcons().add(new Image("com/catcompanion/catIcon.png"));
        stage.setScene(new Scene(root));
        stage.show();

        Cat tmpCat = new Cat();

        ServiceCommands.println(Data.crOutput,
                "Set your cat's name:");
        Data.crInput.setOnAction(e -> {
            String inputText = Data.crInput.getText();
            ServiceCommands.println(Data.crOutput, inputText);
            Data.crInput.clear();

            int id = CatCreation.createCat(Data.cats, tmpCat, inputText);
            if (id != -1) {
                Data.catId = id;
                Data.crOutput.clear();
                stage.close();

                Data.cats.get(Data.catId).catStatus();
                Data.cats.get(Data.catId).catMiniStatus();
            }
        });
    }

    public static void choosePane() {
        VBox root = new VBox(15, Data.chOutput, Data.chInput);

        // Basic Fields and Areas rules
        Data.chOutput.setPrefHeight(250);
        Data.chOutput.setFont(Font.font(16));
        Data.chOutput.setEditable(false);
        Data.chOutput.setFocusTraversable(false);

        root.setPadding(new Insets(15));
        root.setPrefSize(200, 300);
        root.setStyle("-fx-background-color: Beige;");

        Stage stage = new Stage();
        stage.setTitle("Choose");
        stage.getIcons().add(new Image("com/catcompanion/catIcon.png"));
        stage.setScene(new Scene(root));
        stage.show();

        CatCreation.chooseCat(Data.cats);

        if (Data.cats.size() == 1) {
            ServiceCommands.println(Data.output, "There is no cats, please create one\n" +
                    "using create command");
            stage.close();
        }

        Data.chInput.setOnAction(e -> {
            String inputText = Data.chInput.getText();
            Data.chInput.clear();
            Data.catId = Integer.parseInt(inputText);

            Data.chOutput.clear();
            stage.close();

            Data.cats.get(Data.catId).catStatus();
            Data.cats.get(Data.catId).catMiniStatus();
        });

    }

    public static void dancePane() {
        VBox root = new VBox(15, Data.imageViewDance);

        root.setPrefSize(100, 100);
        root.setStyle("-fx-background-color: Beige;");

        Data.imageViewDance.setFitHeight(100);
        Data.imageViewDance.setFitWidth(100);

        Stage stage = new Stage();
        stage.setTitle("Choose");
        stage.getIcons().add(new Image("com/catcompanion/catIcon.png"));
        stage.setScene(new Scene(root));
        stage.setY(519);
        stage.setX(808);
        stage.show();

        Data.cats.get(Data.catId).setHunger(HungerLvls.Lvls[HungerLvls.findIndex(HungerLvls.Lvls, Data.cats.get(Data.catId).getHunger()) - 1]);
        Data.cats.get(Data.catId).setMood(Moods.happy);
    }

    public static void commandsPane() {
        VBox root = new VBox(15, Data.comOutput);

        root.setPadding(new Insets(15));
        root.setPrefSize(180, 400);
        root.setStyle("-fx-background-color: Beige;");

        Data.comOutput.setPrefSize(180, 400);
        Data.comOutput.setFocusTraversable(false);


        Stage stage = new Stage();
        stage.setTitle("Choose");
        stage.getIcons().add(new Image("com/catcompanion/catIcon.png"));
        stage.setScene(new Scene(root));
        stage.setY(149);
        stage.setX(926);
        stage.show();

        ServiceCommands.getCommands();
    }

    public static void secretPane() {
        // dunno
    }
}
