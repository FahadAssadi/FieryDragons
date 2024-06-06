package main.ui.frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameFrame extends JFrame {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 200;
    private static final String FRAME_TITLE = "Fiery Dragons - Start Game";

    private JButton startNewGameButton;
    private JButton loadGameButton;

    public StartGameFrame(ActionListener startNewGameListener, ActionListener loadGameListener) {
        super(FRAME_TITLE);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        startNewGameButton = new JButton("Start New Game");
        startNewGameButton.setBounds(50, 70, 130, 30);
        startNewGameButton.addActionListener(startNewGameListener);
        add(startNewGameButton);

        loadGameButton = new JButton("Load Game");
        loadGameButton.setBounds(220, 70, 130, 30);
        loadGameButton.addActionListener(loadGameListener);
        add(loadGameButton);
    }
}
