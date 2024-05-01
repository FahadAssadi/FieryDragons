package com.fit3077.fierydragons;

import com.fit3077.fierydragons.UI.MainLayout;
import com.fit3077.fierydragons.models.board.BoardManager;
import com.fit3077.fierydragons.models.creatures.CreatureFactory;
import com.fit3077.fierydragons.models.dragonCards.DragonCardsManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        CreatureFactory creatureFactory = new CreatureFactory();
        BoardManager boardManager = new BoardManager(creatureFactory);
        DragonCardsManager dragonCardsManager = new DragonCardsManager(creatureFactory);
        dragonCardsManager.shuffleCards();

        MainLayout mainLayout = new MainLayout(boardManager, dragonCardsManager);

        Scene scene = new Scene(mainLayout.getLayout(24), 1050, 1050);

        // Set up stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fiery Dragons");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}