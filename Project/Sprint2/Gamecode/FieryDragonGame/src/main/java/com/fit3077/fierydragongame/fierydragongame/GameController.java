package com.fit3077.fierydragongame.fierydragongame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private GridPane innerGridPane;
    @FXML
    private FlowPane topPane;
    @FXML
    private FlowPane bottomPane;
    @FXML
    private FlowPane leftPane;
    @FXML
    private FlowPane rightPane;
    @FXML
    private BorderPane borderPaneOut;
    @FXML
    private FlowPane topPaneOut;
    @FXML
    private FlowPane bottomPaneOut;
    @FXML
    private FlowPane leftPaneOut;
    @FXML
    private FlowPane rightPaneOut;

    private static final int CELL_SIZE = 50;
    private static final int TILE_SIZE = 33;
    private static final int CAVE_SIZE = 70;

    private static final String[] ANIMAL_ORDER = {
            "bat", "salamander", "bat", "salamander", "babydragon", "salamander",
            "spider", "bat", "babydragon", "bat", "spider", "babydragon",
            "spider", "bat", "salamander", "babydragon", "spider", "bat",
            "babydragon", "salamander", "spider", "salamander", "babydragon", "spider"
    };
    private static final String[] CHITS = {
            "bat1", "bat2", "bat3", "salamander1", "salamander2", "salamander3",
            "spider1", "spider2", "spider3", "babydragon1", "babydragon2", "babydragon3",
            "dragonpirate1", "dragonpirate2", "dragonpirate1", "dragonpirate2"
    };
    private String[] shuffledChits;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Randomising the chits
        List<String> chitsList = Arrays.asList(CHITS);
        List<String> shuffledChitsList = new ArrayList<>(chitsList);
        Collections.shuffle(shuffledChitsList);
        shuffledChits = shuffledChitsList.toArray(new String[0]);

        populateInnerGrid();
        populateTiles();
    }

    private void populateInnerGrid() {
        // Populate inner grid with images
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                ImageView imageView = new ImageView(new Image("file:images/unflipped.png"));
                imageView.setFitWidth(CELL_SIZE);
                imageView.setFitHeight(CELL_SIZE);
                Pane pane = new Pane(imageView);
                pane.setMinSize(CELL_SIZE, CELL_SIZE);

                // Add click event handler to the Pane
                int finalRow = row;
                int finalCol = col;
                pane.setOnMouseClicked(event -> {
                    // Testing
                    System.out.println("Cell at row " + finalRow + ", column " + finalCol + " clicked.");

                    // Flipping the chit
                    ImageView chitImage = (ImageView) pane.getChildren().get(0);
                    Image fetchedImage = new Image("file:images/" + shuffledChits[finalRow * 4 + finalCol] + ".png");
                    chitImage.setImage(fetchedImage);

                    // Adding a delay before flipping the chit again
                    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
                    pauseTransition.setOnFinished(e -> {
                        // Flipping the chit back
                        chitImage.setImage(new Image("file:images/unflipped.png"));
                    });
                    pauseTransition.play();
                });

                innerGridPane.add(pane, col, row);
            }
        }
    }

    private void populateTiles() {
        // Populate tiles around inner grid with images
        for (int tileNum = 0; tileNum < 24; tileNum++) {
            ImageView tileImage = new ImageView(new Image("file:images/" + ANIMAL_ORDER[tileNum] + "tile.png"));
            tileImage.setFitWidth(TILE_SIZE);
            tileImage.setFitHeight(TILE_SIZE);
            if (tileNum < 7) {
                topPane.getChildren().add(tileImage);
            } else if (tileNum < 12) {
                rightPane.getChildren().add(tileImage);
            } else if (tileNum < 19) {
                bottomPane.getChildren().add(tileImage);
            } else if (tileNum < 24) {
                leftPane.getChildren().add(tileImage);
            }
        }
        // Populate Caves
        ImageView topCave = new ImageView(new Image("file:images/spidercave.png"));
        topCave.setFitWidth(CAVE_SIZE);
        topCave.setFitHeight(CAVE_SIZE);
        ImageView rightCave = new ImageView(new Image("file:images/salamandercave.png"));
        rightCave.setFitWidth(CAVE_SIZE);
        rightCave.setFitHeight(CAVE_SIZE);
        ImageView bottomCave = new ImageView(new Image("file:images/batcave.png"));
        bottomCave.setFitWidth(CAVE_SIZE);
        bottomCave.setFitHeight(CAVE_SIZE);
        ImageView leftCave = new ImageView(new Image("file:images/babydragoncave.png"));
        leftCave.setFitWidth(CAVE_SIZE);
        leftCave.setFitHeight(CAVE_SIZE);

        topPaneOut.getChildren().add(topCave);
        rightPaneOut.getChildren().add(rightCave);
        bottomPaneOut.getChildren().add(bottomCave);
        leftPaneOut.getChildren().add(leftCave);
    }
}