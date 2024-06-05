package main.ui.frames;

import main.game.player.Player;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class GameOverFrame extends JFrame {

    public GameOverFrame(List<Player> players) {
        setTitle("Game Over");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(gameOverLabel, BorderLayout.NORTH);

        // Sorting players according to their scores in descending order
        players.sort((p1, p2) -> Integer.compare(p2.getTotalMoves(), p1.getTotalMoves()));

        // Creating the leaderboard panel
        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));

        // Adding each player to the leaderboard panel
        for (Player player : players) {
            JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel playerLabel = new JLabel( "Player Score: " + player.getTotalMoves());
            playerLabel.setFont(new Font("Serif", Font.PLAIN, 18));

            // Creating a small panel to show the color of the player
            JPanel colorIndicator = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.decode(player.getColour()));
                    g.fillOval(0, 0, 20, 20);
                }
            };
            colorIndicator.setPreferredSize(new Dimension(20, 20));

            playerPanel.add(colorIndicator);
            playerPanel.add(playerLabel);

            leaderboardPanel.add(playerPanel);
        }

        // Add leaderboardPanel to the main panel
        panel.add(leaderboardPanel, BorderLayout.CENTER);

        add(panel);
    }
}
