package main.ui.panels;

import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.Player;
import main.game.tile.TileKeeper;

import javax.swing.*;
import java.awt.*;

/**
 * The PlayerTurnPanel class represents a panel displaying the current player's turn in the main.game UI.
 * It extends the JPanel class and listens for PLAYER_TURN_END events to update the displayed player.
 */
public class PlayerTurnPanel extends JPanel implements EventListener {
    // Player manager to retrieve current player information
    private final TileKeeper tileKeeper;

    // Panel dimensions and offsets
    private static final int xOffset = 0;
    private static final int yOffset = 0;
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 50;
    private static final int FONT_SIZE = 20;


    public PlayerTurnPanel(TileKeeper tileKeeper, int parentWidth, int parentHeight) {
        super();
        this.tileKeeper = tileKeeper;

        // Set panel bounds, background color, and border
        setBounds(xOffset, yOffset, parentWidth, parentHeight);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Subscribe to PLAYER_TURN_END events to update player turn information
        EventManager.getInstance().subscribe(EventType.PLAYER_TURN_END, this);

        // Position the panel at the bottom right corner
        int x = parentWidth - PANEL_WIDTH - 10;
        int y = parentHeight - PANEL_HEIGHT - 30;
        setLocation(x, y);
    }

    /**
     * Paints the component, displaying the current player's turn and color indicator.
     *
     * @param g The Graphics object used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the current player and player color
        Player currentPlayer = tileKeeper.getPlayerTileQueue().getCurrPlayerTileNode().getType().getPlayer();
        String playerColor = currentPlayer.getColour();
        String playerTurnText = "Current Player: ";

        // Draw the player turn text
        g.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        g.setColor(Color.BLACK);
        g.drawString(playerTurnText, 10, 25);

        // Draw the player color indicator
        g.setColor(Color.decode(playerColor));
        g.fillOval(10 + g.getFontMetrics().stringWidth(playerTurnText), 10, FONT_SIZE, FONT_SIZE);
    }

    /**
     * Updates the panel when a PLAYER_TURN_END event is received.
     *
     * @param eventType The type of event received.
     */
    @Override
    public void update(EventType eventType) {
        if (eventType == EventType.PLAYER_TURN_END) {
            // Repaint the panel to update the displayed player
            repaint();
        }
    }
}
