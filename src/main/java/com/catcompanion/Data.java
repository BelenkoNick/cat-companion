package com.catcompanion;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Data {
    // Creates output and input areas
    static Label statusLabel = new Label();
    static TextArea output = new TextArea();
    static TextField input = new TextField();

    static TextArea crOutput = new TextArea();
    static TextField crInput = new TextField();

    static TextArea chOutput = new TextArea();
    static TextField chInput = new TextField();

    // Creates command map
    static Map<String, Command> commands = new LinkedHashMap<String, Command>();

    // Creates array of cats
    static ArrayList<Cat> cats = new ArrayList<>();
    static int catId;
}
