package main.ui.panels;

import main.game.tile.Tile;
import main.game.tile.TileManager;
import main.ui.shapes.TileHexagon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The TilePanel class represents a panel for displaying main.game tiles in the UI.
 * It extends the JPanel class and is responsible for painting volcano and cave tiles.
 */
public class TilePanel extends JPanel {
    // Tile manager to retrieve tile information
    private final TileManager tileManager;

    // Panel dimensions and offsets
    private static final int xOffset = 0;
    private static final int yOffset = 0;
    private static final int TILE_SIZE = 36;
    private static final int RING_RADIUS = 270;

    /**
     * Constructs a TilePanel object with the specified tile manager, parent width, and parent height.
     *
     * @param tileManager  The tile manager containing tile information.
     * @param parentWidth  The width of the parent container.
     * @param parentHeight The height of the parent container.
     */
    public TilePanel(TileManager tileManager, int parentWidth, int parentHeight) {
        super();
        this.tileManager = tileManager;

        // Set panel bounds and background color
        setBounds(xOffset, yOffset, parentWidth, parentHeight);
        setBackground(Color.WHITE);
    }

    /**
     * Paints the component, drawing volcano and cave tiles on the panel.
     *
     * @param g The Graphics object used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the center point of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw volcano tiles
        List<Tile> volcanoTiles = this.tileManager.getVolcanoTileList();
        drawVolcanoTiles(g, centerX, centerY, volcanoTiles);

        // Draw cave tiles
        List<Tile> caveTiles = this.tileManager.getCaveTileList();
        drawCaveTiles(g, centerX, centerY, caveTiles);
    }

    /**
     * Draws volcano tiles around the center of the panel.
     *
     * @param g           The Graphics object used for drawing.
     * @param centerX      The x-coordinate of the center of the panel.
     * @param centerY      The y-coordinate of the center of the panel.
     * @param volcanoTiles The list of volcano tiles to be drawn.
     */
    private void drawVolcanoTiles(Graphics g, int centerX, int centerY, List<Tile> volcanoTiles) {
        int numTiles = volcanoTiles.size();
        double angleIncrement = 2 * Math.PI / numTiles;

        // Start drawing tiles from 12 o'clock (270 degrees)
        double startAngle = Math.PI * 1.5;

        // Draw each volcano tile around the center in a ring
        for (int i = 0; i < numTiles; i++) {
            double angle = startAngle + i * angleIncrement;

            int tileX = (int) (centerX + (RING_RADIUS - TILE_SIZE / 2) * Math.cos(angle));
            int tileY = (int) (centerY + (RING_RADIUS - TILE_SIZE / 2) * Math.sin(angle));

            TileHexagon tileHexagon = new TileHexagon(volcanoTiles.get(i), tileX, tileY);
            tileHexagon.drawTile(g);
        }
    }

    /**
     * Draws cave tiles around the center of the panel.
     *
     * @param g         The Graphics object used for drawing.
     * @param centerX    The x-coordinate of the center of the panel.
     * @param centerY    The y-coordinate of the center of the panel.
     * @param caveTiles  The list of cave tiles to be drawn.
     */
    private void drawCaveTiles(Graphics g, int centerX, int centerY, List<Tile> caveTiles) {
        int numTiles = caveTiles.size();
        double angleIncrement = 2 * Math.PI / numTiles;

        // Start drawing tiles from 12 o'clock (270 degrees)
        double startAngle = Math.PI * 1.5;

        // Set the larger radius for the cave tiles
        int caveRingRadius = RING_RADIUS + TILE_SIZE + 20;

        // Draw each cave tile around the center in a larger ring
        for (int i = 0; i < numTiles; i++) {
            double angle = startAngle + i * angleIncrement;

            int tileX = (int) (centerX + (caveRingRadius - TILE_SIZE / 2) * Math.cos(angle));
            int tileY = (int) (centerY + (caveRingRadius - TILE_SIZE / 2) * Math.sin(angle));

            TileHexagon tileHexagon = new TileHexagon(caveTiles.get(i), tileX, tileY);
            tileHexagon.drawTile(g);
        }
    }
}
