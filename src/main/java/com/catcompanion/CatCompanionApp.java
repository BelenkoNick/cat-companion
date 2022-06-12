package com.catcompanion;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Objects;


public class CatCompanionApp extends Application {
    // Pane creation and starts the game frame
    private Parent basicPane() {

        FlowPane root = new FlowPane(15, 15, Data.statusLabel, Data.imageView, Data.output, Data.input);

        Data.statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Data.statusLabel.setText("");

        Data.output.setPrefSize(350, 200);
        Data.output.setFont(Font.font(16));
        Data.output.setEditable(false);
        Data.output.setFocusTraversable(false);
        Data.output.setStyle("-fx-border-color: Blue;");

        Data.input.setStyle("-fx-border-color: Blue;");
        Data.input.setPrefWidth(350);

        root.setStyle("-fx-background-color: Beige;");
        root.setPadding(new Insets(15));
        root.setPrefSize(350, 350);

        Data.imageView.setFitWidth(50);
        Data.imageView.setFitHeight(50);

        // kostul' for commands to not throw exception with index 0 in length 0
        Cat tmpCat = new Cat();
        Data.cats.add(tmpCat);

        Media sound = new Media(Objects
                .requireNonNull(getClass()
                .getClassLoader()
                .getResource("com/catcompanion/stayWithMe.mp3"))
                .toExternalForm());
        Data.mediaPlayer = new MediaPlayer(sound);
        Data.mediaPlayer.setVolume(0.5);
        Data.mediaPlayer.play();

        // Action receiver
        Data.input.setOnAction(e -> {
            Data.output.clear();
            String inputText = Data.input.getText();
            Data.input.clear();

            ServiceCommands.onInput(Data.commands, inputText);
            CatCommands.catMiniStatus(Data.cats.get(Data.catId));
        });


        CatCommands.catMiniStatus(Data.cats.get(Data.catId));

        initGame();

        return root;
    }
    private void creationPane() {
        VBox root = new VBox(15, Data.crOutput, Data.crInput);

        // Basic Fields and Areas rules
        Data.crOutput.setPrefHeight(250);
        Data.crOutput.setFont(Font.font(16));
        Data.crOutput.setEditable(false);
        Data.crOutput.setFocusTraversable(false);

        root.setPadding(new Insets(15));
        root.setPrefSize(250, 300);

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

                CatCommands.catStatus(Data.cats.get(Data.catId));
                CatCommands.catMiniStatus(Data.cats.get(Data.catId));
            }
        });
    }

    private void choosePane() {
        VBox root = new VBox(15, Data.chOutput, Data.chInput);

        // Basic Fields and Areas rules
        Data.chOutput.setPrefHeight(250);
        Data.chOutput.setFont(Font.font(16));
        Data.chOutput.setEditable(false);
        Data.chOutput.setFocusTraversable(false);

        root.setPadding(new Insets(15));
        root.setPrefSize(200, 300);

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

            CatCommands.catStatus(Data.cats.get(Data.catId));
            CatCommands.catMiniStatus(Data.cats.get(Data.catId));
        });

    }

    // Game initiation
    private void initGame() {
        ServiceCommands.println(Data.output, "Welcome to Cat Companion! v0.2.0fx\n" +
                "\nInput create to create your first cat!" +
                "\nor input help to get extra info on game!");
        initCommands();
    }

    private void newStage(Parent newPane) {
        Stage stage = new Stage();
        stage.setTitle("Cat Creation");
        stage.setScene(new Scene(newPane));
        stage.show();
    }

    // Command initiation
    private void initCommands() {

        // Service Commands
        Data.commands.put("exit", new Command(
                "exit",
                "Exit the game",
                "service",
                ServiceCommands::closeApp));
        Data.commands.put("help", new Command(
                "help",
                "Get basic help info",
                "service",
                ServiceCommands::help));
        Data.commands.put("actions", new Command(
                "action",
                "Lists cat actions",
                "service",
                ServiceCommands::getCommands));

        //Cat Commands
        Data.commands.put("status", new Command(
                "status",
                "Check cat status",
                "cat",
                () -> CatCommands.catStatus(Data.cats.get(Data.catId))));
        Data.commands.put("play", new Command(
                "play",
                "Play with cat",
                "cat",
                () -> CatCommands.playWith(Data.cats.get(Data.catId))));
        Data.commands.put("feed", new Command(
                "feed",
                "Feed cat",
                "cat",
                () -> CatCommands.feedTheCat(Data.cats.get(Data.catId))));
        Data.commands.put("change", new Command(
                "change",
                "Change cat",
                "cat",
                this::choosePane));
        Data.commands.put("create", new Command(
                "create",
                "Create new cat",
                "cat",
                this::creationPane));
        Data.commands.put("meow", new Command(
                "meow",
                "Cat meows",
                "cat",
                () -> CatCommands.meow(Data.cats.get(Data.catId))));


        // Duplicate commands
        Data.commands.put("close", Data.commands.get("exit"));
        Data.commands.put("info", Data.commands.get("help"));
        Data.commands.put("commands", Data.commands.get("actions"));
        Data.commands.put("comms", Data.commands.get("actions"));

        Data.commands.put("choose", Data.commands.get("change"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CatCompanion");
        primaryStage.getIcons().add(new Image("com/catcompanion/catIcon.png"));
        primaryStage.setScene(new Scene(basicPane()));
        primaryStage.show();
    }

    public static class Launcher {
        public static void main(String[] args) {
            Application.launch(CatCompanionApp.class, args);
        }
    }
}
