package com.fit3077.fierydragons;

import com.fit3077.fierydragons.UI.MainLayout;
import com.fit3077.fierydragons.models.event.gameActions.MismatchedAction;
import com.fit3077.fierydragons.models.board.BoardManager;
import com.fit3077.fierydragons.models.creatures.CreatureFactory;
import com.fit3077.fierydragons.models.dragonCards.DragonCardsManager;
import com.fit3077.fierydragons.models.event.EventManager;
import com.fit3077.fierydragons.models.event.EventType;
import com.fit3077.fierydragons.models.player.PlayerManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        CreatureFactory creatureFactory = new CreatureFactory();
        PlayerManager playerManager = new PlayerManager();
        BoardManager boardManager = new BoardManager(creatureFactory, playerManager);
        DragonCardsManager dragonCardsManager = new DragonCardsManager(creatureFactory);

        MainLayout mainLayout = new MainLayout(boardManager, dragonCardsManager);

        // create actions
        MismatchedAction mismatchedAction = new MismatchedAction(playerManager);

        // subscribe to events
        EventManager.getInstance().subscribe(EventType.GAMESTART, dragonCardsManager);
        EventManager.getInstance().subscribe(EventType.MISMATCHED_CREATURE, mismatchedAction);


        EventManager.getInstance().notify(EventType.GAMESTART);
        EventManager.getInstance().notify(EventType.MISMATCHED_CREATURE);

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