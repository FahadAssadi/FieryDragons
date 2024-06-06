package main.ui.frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveGameFrame extends JFrame {
    private static final int FRAME_WIDTH = 550;
    private static final int FRAME_HEIGHT = 350;
    private static final String FRAME_TITLE = "Fiery Dragons - Save Game";

    private JButton saveGame1Button;
    private JButton saveGame2Button;
    private JButton saveGame3Button;

    public SaveGameFrame(ActionListener saveGame1Listener, ActionListener saveGame2Listener, ActionListener saveGame3Listener) {
        super(FRAME_TITLE);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Calculate the x-coordinate for centering the buttons
        int buttonX = (FRAME_WIDTH - 130) / 2;

        saveGame1Button = new JButton("Save Game 1");
        saveGame1Button.setBounds(buttonX, 100, 130, 30);
        saveGame1Button.addActionListener(saveGame1Listener);
        add(saveGame1Button);

        saveGame2Button = new JButton("Save Game 2");
        saveGame2Button.setBounds(buttonX, 140, 130, 30);
        saveGame2Button.addActionListener(saveGame2Listener);
        add(saveGame2Button);

        saveGame3Button = new JButton("Save Game 3");
        saveGame3Button.setBounds(buttonX, 180, 130, 30);
        saveGame3Button.addActionListener(saveGame3Listener);
        add(saveGame3Button);
    }
}
