package com.fit3077.fierydragons.UI;

import com.fit3077.fierydragons.Application;
import com.fit3077.fierydragons.models.board.BoardManager;
import com.fit3077.fierydragons.models.board.Tile;
import com.fit3077.fierydragons.models.dragonCards.DragonCardsManager;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Objects;

public class MainLayout {
    private final BoardManager boardManager;
    private final DragonCardsManager dragonCardsManager;
    private static final int tileSize = 100;

    public MainLayout(BoardManager boardManager, DragonCardsManager dragonCardsManager) {
        this.boardManager = boardManager;
        this.dragonCardsManager = dragonCardsManager;
    }

    public AnchorPane getLayout(int tiles) {
        int x = (tiles - 4) / 4; // The largest integer smaller than or equal to the exact number of inner tiles
        int leftoverTiles = (tiles - 4) % 4; // This will give us the remainder if any

        // Distributing the remainder to the top and bottom tile.
        int topAndBottom = x + 2 + leftoverTiles / 2;

        BorderPane borderPane = new BorderPane();

        // Create GridPanes
        GridPane topGrid = GridPaneFactory.createGridPane(topAndBottom, 1);
        GridPane bottomGrid = GridPaneFactory.createGridPane(topAndBottom, 1);
        GridPane leftGrid = GridPaneFactory.createGridPane(1, x);
        GridPane rightGrid = GridPaneFactory.createGridPane(1, x);

        // Populating GridPanes with Volcano Tiles
        populateGridPane(topGrid, 0, topAndBottom, true, false);
        populateGridPane(rightGrid, topAndBottom, topAndBottom + x, false, false);
        populateGridPane(bottomGrid, topAndBottom + x, 2 * topAndBottom + x, true, true);
        populateGridPane(leftGrid, 2 * topAndBottom + x, 2 * topAndBottom + 2 * x, false, true);

        // Set positions in Borderpane
        borderPane.setTop(topGrid);
        borderPane.setBottom(bottomGrid);
        borderPane.setLeft(leftGrid);
        borderPane.setRight(rightGrid);

        // adding center grid pane
        StackPane centerGrid = CenterGrid.createCenterGrid(dragonCardsManager);
        borderPane.setCenter(centerGrid);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinHeight(1000);
        anchorPane.setMinWidth(1000);

        anchorPane.getChildren().add(borderPane);

        AnchorPane.setLeftAnchor(borderPane, 200.0);
        AnchorPane.setTopAnchor(borderPane, 200.0);

        setupCaveTiles(anchorPane);

        return anchorPane;
    }

    private void populateGridPane(GridPane gridPane, int start, int end, boolean isHorizontal, boolean reverse) {
        List<Tile> volcanoTiles = boardManager.getVolcanoTiles();
        int index = 0;

        if (reverse) {
            for (int i = end - 1; i >= start; i--) {
                Tile tile = volcanoTiles.get(i);
                if (isHorizontal) {
                    gridPane.add(createTilePane(tile), end - 1 - i, 0); // Reversed index for horizontal
                } else {
                    gridPane.add(createTilePane(tile), 0, end - 1 - i); // Reversed index for vertical
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                Tile tile = volcanoTiles.get(i);
                if (isHorizontal) {
                    gridPane.add(createTilePane(tile), index++, 0);  // Only one row, multiple columns
                } else {
                    gridPane.add(createTilePane(tile), 0, index++);  // Only one column, multiple rows
                }
            }
        }
    }

    private StackPane createTilePane(Tile tile) {
        StackPane pane = new StackPane();
        pane.setPrefSize(tileSize, tileSize);

        String imagePath = tile.getCreature().getImagePath();

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream(imagePath))));
        imageView.setFitWidth(tileSize - 2);
        imageView.setFitHeight(tileSize - 2);

        pane.getChildren().addAll(imageView);

        StackPane.setAlignment(imageView, Pos.CENTER);

        return pane;
    }

    public void setupCaveTiles(AnchorPane anchorPane) {
        // Define the positions for each cave tile
        double[][] positions = {
                {300.0, 100.0},
                {900.0, 300.0},
                {700.0, 900.0},
                {100.0, 700.0}
        };

        List<Tile> caveTiles = boardManager.getCaveTiles();

        for (int i = 0; i < positions.length; i++) {
            if (i < caveTiles.size()) {
                createCave(positions[i][0], positions[i][1], caveTiles.get(i), anchorPane);
            }
        }
    }
    private void createCave(Double x, Double y, Tile caveTile, AnchorPane anchorPane) {
        StackPane cavePane = new StackPane();
        cavePane.setPrefSize(tileSize, tileSize);

        String imagePath = caveTile.getCreature().getImagePath();

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream(imagePath))));
        imageView.setFitWidth(tileSize - 2);
        imageView.setFitHeight(tileSize - 2);

        BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3));
        cavePane.setBorder(new Border(borderStroke));

        cavePane.getChildren().addAll(imageView);

        AnchorPane.setLeftAnchor(cavePane, x);
        AnchorPane.setTopAnchor(cavePane, y);
        anchorPane.getChildren().add(cavePane);
    }
}