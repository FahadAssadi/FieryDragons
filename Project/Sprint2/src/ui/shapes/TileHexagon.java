package ui.shapes;

import game.tile.Tile;

import java.awt.*;

public class TileHexagon extends Polygon {
    private final Tile tile;

    private static final int TOTAL_POINTS = 6; // 6 points for a hexagon
    private static final int TILE_SIZE = 36;
    private static final Color TILE_COLOR = new Color(120, 59, 5, 244);

    public TileHexagon(Tile tile, int tileX, int tileY) {
        super();
        this.tile = tile;

        this.npoints = TOTAL_POINTS;
        this.pointsSetup(tileX, tileY);
    }

    private void pointsSetup(int x, int y){
        this.xpoints = new int[]{x - TILE_SIZE / 2, x + TILE_SIZE / 2, x + TILE_SIZE, x + TILE_SIZE / 2, x - TILE_SIZE / 2, x - TILE_SIZE};
        this.ypoints = new int[]{y - TILE_SIZE, y - TILE_SIZE, y, y + TILE_SIZE, y + TILE_SIZE, y};
    }

    public void drawTile(Graphics g){
        g.setColor(TILE_COLOR);
        g.fillPolygon(this);

        // Calculate the center of the hexagon
        int centerX = (xpoints[0] + xpoints[2]) / 2;
        int centerY = (ypoints[0] + ypoints[4]) / 2;

        // Calculate the position to draw the image at the center
        int imageX = centerX - TILE_SIZE / 2;
        int imageY = centerY - TILE_SIZE / 2;

        g.drawImage(this.tile.getImageIcon().getImage(), imageX - 10, imageY, TILE_SIZE, TILE_SIZE, null);
    }

}
