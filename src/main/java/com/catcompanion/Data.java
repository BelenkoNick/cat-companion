package com.catcompanion;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.imageio.stream.ImageInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Data {
    // Creates output and input areas
    static Label statusLabel = new Label();
    static Label barsLabel = new Label();
    static TextArea output = new TextArea();
    static TextField input = new TextField();

    static TextArea crOutput = new TextArea();
    static TextField crInput = new TextField();

    static TextArea chOutput = new TextArea();
    static TextField chInput = new TextField();

    static TextArea comOutput = new TextArea();

    // Creates Media Players for music and sounds
    static MediaPlayer mediaPlayer;
    static MediaPlayer meowPlayer;

    // Creates image
    static Image catImage = (new Image("com/catcompanion/catIconBase.png"));
    static Image catDancing = (new Image("com/catcompanion/catDancing.gif"));

    static ImageView imageView = new ImageView(catImage);
    static ImageView imageViewDance = new ImageView(catDancing);

    // Creates command map
    static Map<String, Command> commands = new LinkedHashMap<String, Command>();

    // Creates array of cats
    static ArrayList<Cat> cats = new ArrayList<>();
    static int catId;
}
