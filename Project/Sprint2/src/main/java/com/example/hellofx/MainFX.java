package com.example.hellofx;

import com.example.hellofx.GameBoardFX;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        float resolution = 1000;
        Group root = new Group();
        Scene scene = new Scene(root, resolution, resolution, Color.ANTIQUEWHITE);
        primaryStage.setTitle("Fiery Dragon Board Game");
        primaryStage.setResizable(false);

        com.example.hellofx.GameBoardFX board = new GameBoardFX(root);
        board.draw();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
