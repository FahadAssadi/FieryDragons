package com.fit3077.fierydragons;

import com.fit3077.fierydragons.UI.MainLayout;
import com.fit3077.fierydragons.models.board.BoardManager;
import com.fit3077.fierydragons.models.creatures.CreatureFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        // Initialize main layout
        MainLayout mainLayout = new MainLayout();
        Scene scene = new Scene(mainLayout.getLayout(24), 1100, 1100);

        // Set up stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fiery Dragons");
        primaryStage.show();

        CreatureFactory creatureFactory = new CreatureFactory();
        BoardManager boardManager = new BoardManager(creatureFactory);
    }

    public static void main(String[] args) {
        launch();
    }
}