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

        JLabel winnerLabel = new JLabel("Winner: ", SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        panel.add(winnerLabel, BorderLayout.CENTER);

        add(panel);
    }
}
