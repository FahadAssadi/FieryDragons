package com.fit3077.fierydragons.UI;

import com.fit3077.fierydragons.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Objects;

/*
Class responsible for creating the grid for the dragon cards
*/
public class CenterGrid {
    public static StackPane createCenterGrid() {
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        GridPane centerGrid = new GridPane();
        centerGrid.setMaxHeight(100);
        centerGrid.setMaxWidth(100);

        // Setting the ColumnConstraints with horizontal growth
        for (int i = 0; i < 4; i++) {
            centerGrid.getColumnConstraints().add(new ColumnConstraints(100, 100, 100, Priority.SOMETIMES, HPos.CENTER, true));
        }

        // Setting the RowConstraints with vertical growth
        for (int i = 0; i < 4; i++) {
            centerGrid.getRowConstraints().add(new RowConstraints(100, 100, 100, Priority.SOMETIMES, null, true));
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Image image = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("imgs/Bat.png")));

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200);
                imageView.setFitHeight(50);
                imageView.setPreserveRatio(true);
                GridPane.setHalignment(imageView, HPos.CENTER); // Center alignment
                centerGrid.add(imageView, i, j);
            }
        }

//        GridPane.setMargin(centerGrid, new Insets(40));

        stackPane.getChildren().add(centerGrid);

        return stackPane;
    }
}