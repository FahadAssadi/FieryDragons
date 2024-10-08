package main.ui.panels;

import main.game.tile.TileKeeper;
import main.game.tile.TileNode;
import main.game.tile.VolcanoCard;
import main.game.tile.iterators.VolcanoCardIterable;
import main.misc.Settings;
import main.ui.shapes.TileHexagon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The TilePanel class represents a panel for displaying game tiles in the UI.
 * It extends the JPanel class and is responsible for painting volcano and cave tiles.
 */
public class TilePanel extends JPanel {
    // Tile manager to retrieve tile information
    private final TileKeeper tileKeeper;

    // Panel dimensions and offsets
    private static final int xOffset = 0;
    private static final int yOffset = 0;
    private static final int TILE_SIZE = 36;
    private static final int RING_RADIUS = 270;
    private static final Color DEFAULT_COLOR = new Color(207, 108, 21, 244);
    private static final Color ALTERNATE_COLOR = new Color(211, 175, 30, 244);
    private static final Color CAVE_COLOR = new Color(48, 48, 48, 244);


    public TilePanel(TileKeeper tileKeeper, int parentWidth, int parentHeight) {
        super();
        this.tileKeeper = tileKeeper;

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
        drawVolcanoTiles(g, centerX, centerY, this.tileKeeper.getVolcanoCardIterable());

        // Draw cave tiles
        drawCaveTiles(g, centerX, centerY, this.tileKeeper.getVolcanoCardIterable());
    }

    /**
     * Draws volcano tiles on the provided Graphics object at the specified center coordinates.
     *
     * @param  g       the Graphics object used for painting
     * @param  centerX the x-coordinate of the center point
     * @param  centerY the y-coordinate of the center point
     * @param  volcanoCardIterable an iterable of VolcanoCard objects representing the volcano tiles
     */
    private void drawVolcanoTiles(Graphics g, int centerX, int centerY, VolcanoCardIterable volcanoCardIterable) {
        long numTiles = (long) Settings.getSetting("BoardSize");
        double angleIncrement = 2 * Math.PI / numTiles;

        // Start drawing tiles from 12 o'clock (270 degrees)
        double startAngle = Math.PI * 1.5;
        int i = 0;
        int cardIndex = 0;

        for (VolcanoCard volcanoCard : volcanoCardIterable) {
            Color color;
            if (cardIndex % 2 == 0) {
                color = DEFAULT_COLOR;
            } else {
                color = ALTERNATE_COLOR;
            }

            for (TileNode volcanoTile : volcanoCard) {
                double angle = startAngle + i++ * angleIncrement;

                int tileX = (int) (centerX + (RING_RADIUS - TILE_SIZE / 2) * Math.cos(angle));
                int tileY = (int) (centerY + (RING_RADIUS - TILE_SIZE / 2) * Math.sin(angle));

                TileHexagon tileHexagon = new TileHexagon(volcanoTile.getType(), tileX, tileY, color);
                tileHexagon.drawTile(g);
            }
            cardIndex --;
        }
    }

    /**
     * Draws cave tiles on the provided Graphics object at the specified center coordinates.
     *
     * @param  g           the Graphics object used for painting
     * @param  centerX     the x-coordinate of the center point
     * @param  centerY     the y-coordinate of the center point
     * @param  volcanoCardIterable an iterable of VolcanoCard objects representing the volcano tiles
     */
    private void drawCaveTiles(Graphics g, int centerX, int centerY, VolcanoCardIterable volcanoCardIterable) {
        long numTiles = (long) Settings.getSetting("BoardSize");

        double angleIncrement = 2 * Math.PI / numTiles;

        // Start drawing tiles from 12 o'clock (270 degrees)
        double startAngle = Math.PI * 1.5;

        // Set the larger radius for the cave tiles
        int caveRingRadius = RING_RADIUS + TILE_SIZE + 20;

        ArrayList<TileNode> volcanoTiles = new ArrayList<>();
        for (VolcanoCard volcanoCard : volcanoCardIterable){
            for (TileNode tileNode : volcanoCard) {
                volcanoTiles.add(tileNode);
            }
        }

        // Draw cave tiles
        for (int i = 0; i < numTiles; i++) {
            TileNode currVolcanoTile = volcanoTiles.get(i);

            if (currVolcanoTile.getAdjacentTile() == null) {
                continue;
            }

            TileNode caveTile = currVolcanoTile.getAdjacentTile();

            System.out.println(caveTile);

            double angle = startAngle + i * angleIncrement;

            int tileX = (int) (centerX + (caveRingRadius - TILE_SIZE / 2) * Math.cos(angle));
            int tileY = (int) (centerY + (caveRingRadius - TILE_SIZE / 2) * Math.sin(angle));

            TileHexagon tileHexagon = new TileHexagon(caveTile.getType(), tileX, tileY, CAVE_COLOR);
            tileHexagon.drawTile(g);
        }

    }

}
