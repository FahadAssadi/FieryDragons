package ui.frames;

import game.chit.DragonCardManager;
import game.player.PlayerManager;
import game.tile.TileManager;
import ui.panels.DragonCardPanel;
import ui.panels.PlayerTurnPanel;
import ui.panels.TilePanel;

import javax.swing.*;
import java.awt.*;

/**
 * The main frame of the game UI, responsible for managing and displaying various game panels.
 */
public class GameFrame extends JFrame {
    private static final int DEFAULT_FRAME_WIDTH = 800;
    private static final int DEFAULT_FRAME_HEIGHT = 800;
    private static final String GAME_TITLE = "Fiery Dragons";
    private static final String GAME_LOGO_PATH = "Project/Sprint2/src/resources/assets/pngs/logo/FieryDragonsLogo.png";

    /**
     * Constructs a new GameFrame with default dimensions and title.
     * Initializes and adds game panels to the frame.
     */
    public GameFrame() {
        super(GAME_TITLE);

        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        // Set the icon image
        setIconImage(new ImageIcon(GAME_LOGO_PATH).getImage());

        getContentPane().setBackground(Color.white);

        setLayout(null);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates and adds a panel displaying dragon cards to the game frame.
     *
     * @param dragonCardManager The manager for dragon cards.
     */
    public void createDragonCardPanel(DragonCardManager dragonCardManager){
        DragonCardPanel dragonCardPanel = new DragonCardPanel(dragonCardManager, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(dragonCardPanel);
    }

    /**
     * Creates and adds a panel displaying player turn information to the game frame.
     *
     * @param playerManager The manager for players.
     */
    public void createPlayerTurnPanel(PlayerManager playerManager) {
        PlayerTurnPanel playerTurnPanel = new PlayerTurnPanel(playerManager, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(playerTurnPanel);
    }

    /**
     * Creates and adds a panel displaying game tiles to the game frame.
     *
     * @param tileManager The manager for game tiles.
     */
    public void createTilePanel(TileManager tileManager) {
        TilePanel tilePanel = new TilePanel(tileManager, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(tilePanel);
    }
}
