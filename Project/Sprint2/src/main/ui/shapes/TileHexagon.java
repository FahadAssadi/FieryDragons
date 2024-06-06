package main.ui.shapes;

import main.game.player.Player;
import main.game.tile.type.TileType;

import java.awt.*;

public class TileHexagon extends Polygon {
    private final TileType tile;
    private final Color color;

    private static final int TOTAL_POINTS = 6; // 6 points for a hexagon
    private static final int TILE_SIZE = 36;

    public TileHexagon(TileType tile, int tileX, int tileY, Color color) {
        super();
        this.tile = tile;

        this.npoints = TOTAL_POINTS;
        this.pointsSetup(tileX, tileY);
        this.color = color;
    }

    /**
     * Sets up the x and y coordinates of the points that define the hexagon shape.
     *
     * @param  x  the x-coordinate of the center of the hexagon
     * @param  y  the y-coordinate of the center of the hexagon
     */
    private void pointsSetup(int x, int y){
        this.xpoints = new int[]{x - TILE_SIZE / 2, x + TILE_SIZE / 2, x + TILE_SIZE, x + TILE_SIZE / 2, x - TILE_SIZE / 2, x - TILE_SIZE};
        this.ypoints = new int[]{y - TILE_SIZE, y - TILE_SIZE, y, y + TILE_SIZE, y + TILE_SIZE, y};
    }

    /**
     * Draws a tile on the given Graphics object.
     *
     * @param  g  the Graphics object used for painting
     */
    public void drawTile(Graphics g){
        g.setColor(color);
        g.fillPolygon(this);

        // Calculate the center of the hexagon
        int centerX = (xpoints[0] + xpoints[2]) / 2;
        int centerY = (ypoints[0] + ypoints[4]) / 2;

        // Calculate the position to draw the image at the center
        int imageX = centerX - TILE_SIZE / 2;
        int imageY = centerY - TILE_SIZE / 2;

        g.drawImage(this.tile.getImageIcon().getImage(), imageX - 10, imageY, TILE_SIZE, TILE_SIZE, null);

        // Draw the player indicator
        Player player = this.tile.getPlayer();

        if (player != null) {
            DragonToken dragonToken = new DragonToken(player);
            dragonToken.drawPlayerIndicator(g, centerX, centerY);
        }
    }
}
