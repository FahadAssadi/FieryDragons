package main.ui.frames;

import main.game.TicTacToeModel;
import main.game.player.Player;
import javax.swing.*;
import java.awt.*;


public class TicTacToeGameFrame extends JFrame {
    private final TicTacToeModel model;
    private final JButton[][] boardButtons;
    private final JLabel statusLabel;
    private final JPanel statusPanel;

    public TicTacToeGameFrame(Player playerX, Player playerO) {
        this.model = new TicTacToeModel(playerX, playerO);

        setTitle("Collision Solver");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        statusLabel = new JLabel();
        updateStatusLabel();
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        boardButtons = new JButton[3][3];
        initializeBoard(boardPanel);
        add(boardPanel, BorderLayout.CENTER);

        // Adding this here in case we decided to provide the ability to reset game if tie.
        JButton resetButton = new JButton("Reset Game");
        resetButton.addActionListener(e -> handleResetButton());
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initializeBoard(JPanel boardPanel) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, 60));
                button.setFocusPainted(false);
                button.addActionListener(e -> handleButtonClick(index));
                boardButtons[row][col] = button;
                boardPanel.add(button);
            }
        }
    }

    private void handleButtonClick(int index) {
        if (model.getCell(index).isEmpty() && model.getWinner() == null) {
            model.makeMove(index);
            updateBoard();
            updateStatusLabel();
            if (model.getWinner() != null) {
                statusLabel.setText("Winner: " + model.getWinner());
            } else if (model.isBoardFull()) {
                statusLabel.setText("It's a tie!");
            }
        }
    }

    private void updateBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                boardButtons[row][col].setText(model.getCell(index));
            }
        }
    }

    private void updateStatusLabel() {
        statusPanel.removeAll(); // Clear the statusPanel

        if (model.getWinner() == null) {
            Player currentPlayer = model.getCurrentPlayer();
            JLabel playerLabel = new JLabel("Current Player: ");
            playerLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            JPanel colorIndicator = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.decode(currentPlayer.getColour()));
                    g.fillOval(0, 0, 20, 20);
                }
            };
            colorIndicator.setPreferredSize(new Dimension(20, 20));

            statusPanel.add(playerLabel);
            statusPanel.add(colorIndicator);
        } else {
            JLabel winnerLabel = new JLabel("Winner: ");
            winnerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            statusPanel.add(winnerLabel);

            Player winner = model.getWinner();
            JPanel colorIndicator = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.decode(winner.getColour()));
                    g.fillOval(0, 0, 20, 20);
                }
            };
            colorIndicator.setPreferredSize(new Dimension(20, 20));
            statusPanel.add(colorIndicator);
        }

        statusPanel.revalidate();
        statusPanel.repaint();
    }

    private void handleResetButton() {
        model.resetGame();
        updateBoard();
        updateStatusLabel();
    }

    public Player getWinner() {
        return model.getWinner();
    }
}