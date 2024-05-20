package main.ui.shapes;

import main.game.player.Player;

import java.awt.*;

public class DragonToken {
    private static final int TILE_SIZE = 36;
    private Player player;

    public DragonToken(Player player) {
        this.player = player;
    }

    public void drawPlayerIndicator(Graphics g, int centerX, int centerY) {
        g.setColor(Color.decode(player.getColour()));
        int indicatorSize = TILE_SIZE / 2;
        g.fillOval(centerX - indicatorSize / 2, centerY - indicatorSize / 2, indicatorSize, indicatorSize);
    }
}
