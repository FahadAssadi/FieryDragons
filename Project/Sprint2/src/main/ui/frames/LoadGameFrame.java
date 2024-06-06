package main.ui.frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadGameFrame extends JFrame {
    private static final int FRAME_WIDTH = 550;
    private static final int FRAME_HEIGHT = 350;
    private static final String FRAME_TITLE = "Fiery Dragons - Load Game";

    private JButton loadGame1Button;
    private JButton loadGame2Button;
    private JButton loadGame3Button;

    public LoadGameFrame(ActionListener loadGame1Listener, ActionListener loadGame2Listener, ActionListener loadGame3Listener) {
        super(FRAME_TITLE);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Calculate the x-coordinate for centering the buttons
        int buttonX = (FRAME_WIDTH - 130) / 2;

        loadGame1Button = new JButton("Load Game 1");
        loadGame1Button.setBounds(buttonX, 100, 130, 30);
        loadGame1Button.addActionListener(loadGame1Listener);
        add(loadGame1Button);

        loadGame2Button = new JButton("Load Game 2");
        loadGame2Button.setBounds(buttonX, 140, 130, 30);
        loadGame2Button.addActionListener(loadGame2Listener);
        add(loadGame2Button);

        loadGame3Button = new JButton("Load Game 3");
        loadGame3Button.setBounds(buttonX, 180, 130, 30);
        loadGame3Button.addActionListener(loadGame3Listener);
        add(loadGame3Button);
    }
}
