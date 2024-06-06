package main.ui.frames;

import main.game.GameBoard;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.tile.TileKeeper;
import main.ui.panels.DragonCardPanel;
import main.ui.panels.PlayerTurnPanel;
import main.ui.panels.TilePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The main frame of the game UI, responsible for managing and displaying various main.game panels.
 */
public class GameFrame extends JFrame implements EventListener {
    private static final int DEFAULT_FRAME_WIDTH = 800;
    private static final int DEFAULT_FRAME_HEIGHT = 800;
    private static final String GAME_TITLE = "Fiery Dragons";
    private static final String GAME_LOGO_PATH = "/assets/pngs/logo/FieryDragonsLogo.png";

    /**
     * Constructs a new GameFrame with default dimensions and title.
     * Initializes and adds game panels to the frame.
     */
    public GameFrame() {
        super(GAME_TITLE);

        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        // Set the icon image
        setIconImage(new ImageIcon(getClass().getResource(GAME_LOGO_PATH)).getImage());

        setResizable(false);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EventManager.getInstance().subscribe(EventType.PLAYER_MOVED, this);

        createSaveButton();
    }

    /**
     * Creates a save button and adds it to the frame.
     *
     * @return  void
     */
    private void createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(10, DEFAULT_FRAME_HEIGHT - 80, 80, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game Saved!");
//                EventManager.getInstance().notify(EventType.SAVE);
                SaveGameFrame saveGameFrame = new SaveGameFrame(new SaveGame1Listener(), new SaveGame2Listener(), new SaveGame3Listener());
                saveGameFrame.setVisible(true);
            }
        });

        this.add(saveButton);
    }

    private class SaveGame1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Save game 1 functionality to be implemented
            System.out.println("Save Game 1 button clicked!");

            LoadAndSave.getInstance().setSaveFileNumber(1);
            EventManager.getInstance().notify(EventType.SAVE);
        }
    }

    private class SaveGame2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Save game 2 functionality to be implemented
            System.out.println("Save Game 2 button clicked!");

            LoadAndSave.getInstance().setSaveFileNumber(2);
            EventManager.getInstance().notify(EventType.SAVE);
        }
    }

    private class SaveGame3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Save game 3 functionality to be implemented
            System.out.println("Save Game 3 button clicked!");

            LoadAndSave.getInstance().setSaveFileNumber(3);
            EventManager.getInstance().notify(EventType.SAVE);
        }
    }

    /**
     * Creates a DragonCardPanel and adds it to the current frame.
     *
     * @param  gameBoard  the GameBoard object to be passed to the DragonCardPanel constructor
     */
    public void createDragonCardPanel(GameBoard gameBoard){
        DragonCardPanel dragonCardPanel = new DragonCardPanel(gameBoard, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(dragonCardPanel);
    }

    /**
     * Creates a PlayerTurnPanel and adds it to the current frame.
     *
     * @param  tileKeeper  the TileKeeper object to be passed to the PlayerTurnPanel constructor
     */
    public void createPlayerTurnPanel(TileKeeper tileKeeper) {
        PlayerTurnPanel playerTurnPanel = new PlayerTurnPanel(tileKeeper, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(playerTurnPanel);
    }

    /**
     * Creates a TilePanel and adds it to the current frame.
     *
     * @param  tileKeeper  the TileKeeper object to be passed to the TilePanel constructor
     */
    public void createTilePanel(TileKeeper tileKeeper) {
        TilePanel tilePanel = new TilePanel(tileKeeper, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(tilePanel);
    }

    /**
     * Updates the state of the component based on the given event type.
     *
     * @param  eventType  the type of event that occurred
     */
    @Override
    public void update(EventType eventType) {
        if (eventType == EventType.PLAYER_MOVED) {
            repaint();
        }
    }
}
