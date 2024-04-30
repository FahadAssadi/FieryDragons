package ui.panels;

import game.tile.Tile;
import game.tile.TileManager;
import ui.shapes.TileHexagon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TilePanel extends JPanel {
    private final TileManager tileManager;

    private static final int xOffset = 0;
    private static final int yOffset = 0;
    private static final int TILE_SIZE = 36;
    private static final int RING_RADIUS = 270;

    public TilePanel(TileManager tileManager, int parentWidth, int parentHeight) {
        super();
        this.tileManager = tileManager;

        setBounds(xOffset, yOffset, parentWidth, parentHeight);
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the center point of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        List<Tile> volcanoTiles = this.tileManager.getVolcanoTileList();
        int numTiles = volcanoTiles.size();
        double angleIncrement = 2 * Math.PI / numTiles;

        // Start drawing tiles from 12 o'clock (270 degrees)
        double startAngle = Math.PI * 1.5;

        for (int i = 0; i < numTiles; i++) {
            double angle = startAngle + i * angleIncrement;

            int tileX = (int) (centerX + (RING_RADIUS - TILE_SIZE / 2) * Math.cos(angle));
            int tileY = (int) (centerY + (RING_RADIUS - TILE_SIZE / 2) * Math.sin(angle));

            TileHexagon tileHexagon = new TileHexagon(volcanoTiles.get(i), tileX, tileY);
            tileHexagon.drawTile(g);
        }

        List<Tile> caveTiles = this.tileManager.getCaveTileList();

        for (int j = 0; j < caveTiles.size(); j++) {

        }
    }

}