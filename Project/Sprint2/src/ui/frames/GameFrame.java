package ui.frames;

import game.chit.DragonCardManager;
import game.player.PlayerManager;
import game.tile.TileManager;
import ui.panels.DragonCardPanel;
import ui.panels.PlayerTurnPanel;
import ui.panels.TilePanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static final int DEFAULT_FRAME_WIDTH = 800;
    private static final int DEFAULT_FRAME_HEIGHT = 800;
    private static final String GAME_TITLE = "Fiery Dragons";
    private static final String GAME_LOGO_PATH = "Project/Sprint2/src/resources/assets/pngs/logo/FieryDragonsLogo.png";

    public GameFrame() {
        super(GAME_TITLE);

        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        setIconImage(new ImageIcon(GAME_LOGO_PATH).getImage());

        getContentPane().setBackground(Color.white);

        setLayout(null);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createDragonCardPanel(DragonCardManager dragonCardManager){
        DragonCardPanel dragonCardPanel = new DragonCardPanel(dragonCardManager, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(dragonCardPanel);
    }

    public void createPlayerTurnPanel(PlayerManager playerManager) {
        PlayerTurnPanel playerTurnPanel = new PlayerTurnPanel(playerManager, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(playerTurnPanel);
    }

    public void createTilePanel(TileManager tileManager) {
        TilePanel tilePanel = new TilePanel(tileManager, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        this.add(tilePanel);
    }
}
