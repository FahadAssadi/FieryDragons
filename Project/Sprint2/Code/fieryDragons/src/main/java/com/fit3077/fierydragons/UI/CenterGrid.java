package com.fit3077.fierydragons.UI;

import com.fit3077.fierydragons.Application;
import com.fit3077.fierydragons.models.board.BoardManager;
import com.fit3077.fierydragons.models.dragonCards.DragonCard;
import com.fit3077.fierydragons.models.dragonCards.DragonCardsManager;
import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.List;
import java.util.Objects;

/*
Class responsible for creating the grid for the dragon cards
*/
public class CenterGrid {
    private final int size = 70;
    public StackPane createCenterGrid(DragonCardsManager dragonCardsManager) {
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        GridPane centerGrid = new GridPane();
        centerGrid.setMaxHeight(size);
        centerGrid.setMaxWidth(size);

        // Setting the ColumnConstraints with horizontal growth
        for (int i = 0; i < 4; i++) {
            centerGrid.getColumnConstraints().add(new ColumnConstraints(size, size, size, Priority.SOMETIMES, HPos.CENTER, true));
        }

        // Setting the RowConstraints with vertical growth
        for (int i = 0; i < 4; i++) {
            centerGrid.getRowConstraints().add(new RowConstraints(size, size, size, Priority.SOMETIMES, null, true));
        }

        List<DragonCard> dragonCards = dragonCardsManager.getDragonCards();

        // the current dragon card
        int cardIndex = 0;

        Image hiddenCard = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("imgs/HiddenChit.png")));

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageView imageView;

                DragonCard currentCard = dragonCards.get(cardIndex);
                String imagePath = currentCard.getImagePath();

                Image cardImage = new Image(Objects.requireNonNull(Application.class.getResourceAsStream(imagePath)));
                imageView = new ImageView(hiddenCard);
                cardIndex++;
                imageView.setFitWidth(200);
                imageView.setFitHeight(50);
                imageView.setPreserveRatio(true);
                GridPane.setHalignment(imageView, HPos.CENTER);

                StackPane cellStack = new StackPane(imageView);
                cellStack.setOnMouseClicked(event -> {
                    dragonCardsManager.setDragonCard(currentCard);

                    Image currentImage = imageView.getImage();
                    imageView.setImage(currentImage.equals(hiddenCard) ? cardImage : hiddenCard);
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.4));
                    pause.setOnFinished(e -> imageView.setImage(hiddenCard));
                    pause.play();
                });

                centerGrid.add(cellStack, i, j);
            }
        }

        stackPane.getChildren().add(centerGrid);

        return stackPane;
    }
}