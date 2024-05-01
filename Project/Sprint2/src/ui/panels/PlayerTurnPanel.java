package ui.panels;

import game.event.EventListener;
import game.event.EventManager;
import game.event.EventType;
import game.player.Player;
import game.player.PlayerManager;

import javax.swing.*;
import java.awt.*;

public class PlayerTurnPanel extends JPanel implements EventListener {
    private final PlayerManager playerManager;

    private static final int xOffset = 0;
    private static final int yOffset = 0;
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 50;
    private static final int FONT_SIZE = 20;

    public PlayerTurnPanel(PlayerManager playerManager, int parentWidth, int parentHeight) {
        super();
        this.playerManager = playerManager;

        setBounds(xOffset, yOffset, parentWidth, parentHeight);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        EventManager.getInstance().subscribe(EventType.PLAYER_TURN_END, this);

        // Position the panel at the bottom right corner
        int x = parentWidth - PANEL_WIDTH - 10;
        int y = parentHeight - PANEL_HEIGHT - 30;
        setLocation(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

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

    @Override
    public void update(EventType eventType) {
        repaint();
    }
}