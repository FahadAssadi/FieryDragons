package main.ui.shapes;

import main.game.player.Player;

import java.awt.*;

public class DragonToken {
    private static final int TILE_SIZE = 36;
    private Player player;

    public DragonToken(Player player) {
        this.player = player;
    }

    /**
     * Draws a player indicator on the given Graphics object at the specified center coordinates.
     *
     * @param  g  the Graphics object used for painting
     * @param  centerX  the x-coordinate of the center of the indicator
     * @param  centerY  the y-coordinate of the center of the indicator
     */
    public void drawPlayerIndicator(Graphics g, int centerX, int centerY) {
        g.setColor(Color.decode(player.getColour()));
        int indicatorSize = TILE_SIZE / 2;
        g.fillOval(centerX - indicatorSize / 2, centerY - indicatorSize / 2, indicatorSize, indicatorSize);
    }
}
