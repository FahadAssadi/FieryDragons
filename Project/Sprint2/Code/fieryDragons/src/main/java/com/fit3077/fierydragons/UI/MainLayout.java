package com.fit3077.fierydragons.UI;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MainLayout {

    public AnchorPane getLayout(int tiles) {
        int x = (tiles - 4) / 4; // The largest integer smaller than or equal to the exact number of inner tiles
        int leftoverTiles = (tiles - 4) % 4; // This will give us the remainder if any

        // Distributing the remainder to the top and bottom tile.
        int topAndBottom = x + 2 + leftoverTiles / 2;

        BorderPane borderPane = new BorderPane();

        // Top and Bottom GridPane
        GridPane topGrid = GridPaneFactory.createGridPane(topAndBottom, 1);
        GridPane bottomGrid = GridPaneFactory.createGridPane(topAndBottom, 1);
        borderPane.setTop(topGrid);
        borderPane.setBottom(bottomGrid);

        // Left and Right GridPane
        GridPane leftGrid = GridPaneFactory.createGridPane(1, x);
        GridPane rightGrid = GridPaneFactory.createGridPane(1, x);
        borderPane.setLeft(leftGrid);
        borderPane.setRight(rightGrid);

        // adding center grid pane
        StackPane centerGrid = CenterGrid.createCenterGrid();
        borderPane.setCenter(centerGrid);


        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinHeight(1000);
        anchorPane.setMinWidth(1000);

        anchorPane.getChildren().add(borderPane);

        AnchorPane.setLeftAnchor(borderPane, 200.0);
        AnchorPane.setTopAnchor(borderPane, 120.0);


        return anchorPane;
    }
}