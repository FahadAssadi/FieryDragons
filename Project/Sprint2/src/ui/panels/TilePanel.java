package ui.panels;

import game.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private final TileManager tileManager;
    private static final int DEFAULT_PANEL_WIDTH = 500;
    private static final int DEFAULT_PANEL_HEIGHT = 500;
    private static final int RING_RADIUS = 220;
    private static final int RING_THICKNESS = 1;
    private static final Color RING_COLOR = Color.BLACK;

    public TilePanel(TileManager tileManager, int parentWidth, int parentHeight) {
        super();
        this.tileManager = tileManager;

        setPanelBounds(parentWidth, parentHeight);
        setBackground(Color.WHITE);
    }

    private void setPanelBounds(int parentWidth, int parentHeight){
        int x = (parentWidth - DEFAULT_PANEL_WIDTH) / 2;
        int y = (parentHeight - DEFAULT_PANEL_HEIGHT) / 2;

        setBounds(x, y, DEFAULT_PANEL_WIDTH, DEFAULT_PANEL_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Calculate the center point of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Calculate the position of the ring to center it on the panel
        int ringX = centerX - RING_RADIUS;
        int ringY = centerY - RING_RADIUS;

        g2d.setColor(RING_COLOR);
        g2d.setStroke(new BasicStroke(RING_THICKNESS));
        g2d.drawOval(ringX, ringY, 2 * RING_RADIUS, 2 * RING_RADIUS);
    }
}