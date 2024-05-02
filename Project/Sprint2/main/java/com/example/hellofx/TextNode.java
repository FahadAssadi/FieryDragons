package com.example.hellofx;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextNode {
    public void draw(Group root) {
        Text text = new Text();
        text.setText("Welcome To Fiery Dragons");
        text.setX(200);
        text.setY(50);
        text.setFont(Font.font("Verdana", 50));
        text.setFill(Color.GRAY);

        root.getChildren().add(text);
    }
}
