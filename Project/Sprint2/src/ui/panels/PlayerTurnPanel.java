package ui.panels;

import game.event.EventListener;
import game.event.EventManager;
import game.event.EventType;
import game.player.Player;
import game.player.PlayerManager;

import javax.swing.*;
import java.awt.*;

/**
 * The PlayerTurnPanel class represents a panel displaying the current player's turn in the game UI.
 * It extends the JPanel class and listens for PLAYER_TURN_END events to update the displayed player.
 */
public class PlayerTurnPanel extends JPanel implements EventListener {
    // Player manager to retrieve current player information
    private final PlayerManager playerManager;

    // Panel dimensions and offsets
    private static final int xOffset = 0;
    private static final int yOffset = 0;
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 50;
    private static final int FONT_SIZE = 20;

    /**
     * Constructs a PlayerTurnPanel object with the specified player manager, parent width, and parent height.
     * Initializes the panel's appearance and subscribes to PLAYER_TURN_END events.
     *
     * @param playerManager The player manager containing player information.
     * @param parentWidth   The width of the parent container.
     * @param parentHeight  The height of the parent container.
     */
    public PlayerTurnPanel(PlayerManager playerManager, int parentWidth, int parentHeight) {
        super();
        this.playerManager = playerManager;

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
        Player currentPlayer = playerManager.getCurrPlayer();
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
        // Repaint the panel to update the displayed player
        repaint();
    }
}
