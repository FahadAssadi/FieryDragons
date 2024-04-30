package com.fit3077.fierydragons.UI;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class GridPaneFactory {
    public static GridPane createGridPane(int numCols, int numRows) {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

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
}