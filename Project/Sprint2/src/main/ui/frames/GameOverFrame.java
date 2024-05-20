package main.ui.frames;

import main.game.player.Player;

import javax.swing.*;
import java.awt.*;

public class GameOverFrame extends JFrame {

    public GameOverFrame(Player winner) {
        setTitle("Game Over");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel gameOverLabel = new JLabel("Game Won!", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(gameOverLabel, BorderLayout.NORTH);

        // Creating a sub-panel to hold both the label and the color indicator
        JPanel winnerPanel = new JPanel(new FlowLayout());

        JLabel winnerLabel = new JLabel("Winner: ", SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Serif", Font.PLAIN, 18));

        // Creating a small panel to show the color of the winner
        JPanel colorIndicator = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.decode(winner.getColour()));
                g.fillOval(0, 0, 20, 20);
            }
        };
        colorIndicator.setPreferredSize(new Dimension(20, 20));

        // Add components to the winner panel
        winnerPanel.add(winnerLabel);
        winnerPanel.add(colorIndicator);

        // Add winnerPanel to the main panel
        panel.add(winnerPanel, BorderLayout.CENTER);

        add(panel);
    }
}
