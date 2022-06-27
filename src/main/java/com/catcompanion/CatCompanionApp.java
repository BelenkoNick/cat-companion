package com.catcompanion;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;


public class CatCompanionApp extends Application {
    // Pane creation and starts the game frame
    private Parent basicPane() {

        FlowPane root = new FlowPane(15, 15, Data.statusLabel, Data.imageView, Data.barsLabel, Data.output, Data.input);

        Data.statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Data.statusLabel.setText("");

        Data.barsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Data.barsLabel.setText("");
        Data.barsLabel.setTextAlignment(TextAlignment.RIGHT);

        Data.output.setPrefSize(300, 200);
        Data.output.setFont(Font.font(16));
        Data.output.setEditable(false);
        Data.output.setFocusTraversable(false);
        Data.output.setStyle("-fx-border-color: Blue;");

        Data.input.setStyle("-fx-border-color: Blue;");
        Data.input.setPrefWidth(300);

        root.setStyle("-fx-background-color: Beige;");
        root.setPadding(new Insets(15));
        root.setPrefSize(300, 300);

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
            Data.cats.get(Data.catId).catMiniStatus();
        });


        Data.cats.get(Data.catId).catMiniStatus();

        initGame();

        return root;
    }

    // Game initiation
    private void initGame() {
        ServiceCommands.println(Data.output, "Welcome to Cat Companion! v0.3.0fx\n" +
                "\nInput \"create\" to get your first cat!" +
                "\nInput \"help\" to get extra info on game!");
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
                Panes::commandsPane));

        //Cat Commands
        Data.commands.put("status", new Command(
                "status",
                "Check cat status",
                "cat",
                () -> Data.cats.get(Data.catId).catStatus()));
        Data.commands.put("play", new Command(
                "play",
                "Play with cat",
                "cat",
                () -> Data.cats.get(Data.catId).playWith()));
        Data.commands.put("feed", new Command(
                "feed",
                "Feed cat",
                "cat",
                () -> Data.cats.get(Data.catId).feedTheCat()));
        Data.commands.put("change", new Command(
                "change",
                "Change cat",
                "cat",
                Panes::choosePane));
        Data.commands.put("create", new Command(
                "create",
                "Create new cat",
                "cat",
                Panes::creationPane));
        Data.commands.put("meow", new Command(
                "meow",
                "Cat meows",
                "cat",
                () -> Data.cats.get(Data.catId).meow()));
        Data.commands.put("dance", new Command(
                "dance",
                "Cat dances",
                "cat",
                Panes::dancePane));


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
