package com.fit3077.fierydragons;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BoardLayoutBackup extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        int tiles = 24;
        int x = (tiles - 4) / 4; // The largest integer smaller than or equal to the exact number of inner tiles
        int leftoverTiles = (tiles - 4) % 4; // This will give us the remainder if any

        // Distributing the remainder to the top and bottom tile.
        int topAndBottom = x + 2 + leftoverTiles / 2;
        int leftAndRight = x;

        BorderPane borderPane = new BorderPane();

        // Top and Bottom GridPane
        GridPane topGrid = createGridPane(topAndBottom, 1);
        GridPane bottomGrid = createGridPane(topAndBottom, 1);
        borderPane.setTop(topGrid);
        borderPane.setBottom(bottomGrid);

        // Left and Right GridPane
        GridPane leftGrid = createGridPane(1, leftAndRight);
        GridPane rightGrid = createGridPane(1, leftAndRight);
        borderPane.setLeft(leftGrid);
        borderPane.setRight(rightGrid);

        // adding center grid pane
        GridPane centerGrid = createCenterGrid();
        borderPane.setCenter(centerGrid);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinHeight(1000);
        anchorPane.setMinWidth(1000);

        AnchorPane.setLeftAnchor(borderPane, 200.0);
        AnchorPane.setTopAnchor(borderPane, 120.0);

        anchorPane.getChildren().add(borderPane);

        Scene scene = new Scene(anchorPane, 1100, 1100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fiery Dragons");
        primaryStage.show();
    }

    private GridPane createGridPane(int numCols, int numRows) throws IOException {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        BorderPane.setAlignment(grid, Pos.CENTER);

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints column = new ColumnConstraints(100, 100, 100);
            column.setHgrow(Priority.NEVER);
            grid.getColumnConstraints().add(column);
        }

        for (int j = 0; j < numRows; j++) {
            RowConstraints row = new RowConstraints(100, 100, 100);
            row.setVgrow(Priority.NEVER);
            grid.getRowConstraints().add(row);
        }

        return grid;
    }

    private GridPane createCenterGrid() {
        GridPane centerGrid = new GridPane();
//        centerGrid.setGridLinesVisible(true);

        // Setting the ColumnConstraints with horizontal growth
        for (int i = 0; i < 4; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.SOMETIMES);
            columnConstraints.setMinWidth(10);
            columnConstraints.setPrefWidth(50);
            centerGrid.getColumnConstraints().add(columnConstraints);
        }

        // Setting the RowConstraints with vertical growth
        for (int i = 0; i < 4; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            rowConstraints.setMinHeight(10);
            rowConstraints.setPrefHeight(30);
            centerGrid.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/Bat_1.png")));

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200);
                imageView.setFitHeight(50);
                imageView.setPreserveRatio(true);
                GridPane.setHalignment(imageView, HPos.CENTER); // Center alignment
                centerGrid.add(imageView, i, j);
            }
        }

        // Setting margin for the GridPane
        BorderPane.setMargin(centerGrid, new Insets(40, 40, 40, 40));

        return centerGrid;
    }
    public static void main(String[] args) {
        launch();
    }
}
