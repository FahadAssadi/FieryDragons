package main.ui.frames;

import main.game.chit.DragonCardKeeper;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.tile.TileKeeper;
import main.ui.panels.DragonCardPanel;
import main.ui.panels.PlayerTurnPanel;
import main.ui.panels.TilePanel;

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
    }

    public void createDragonCardPanel(DragonCardKeeper dragonCardKeeper){
        DragonCardPanel dragonCardPanel = new DragonCardPanel(dragonCardKeeper, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(dragonCardPanel);
    }


    public void createPlayerTurnPanel(TileKeeper tileKeeper) {
        PlayerTurnPanel playerTurnPanel = new PlayerTurnPanel(tileKeeper, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(playerTurnPanel);
    }


    public void createTilePanel(TileKeeper tileKeeper) {
        TilePanel tilePanel = new TilePanel(tileKeeper, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(tilePanel);
    }


    @Override
    public void update(EventType eventType) {
        repaint();
    }
}
