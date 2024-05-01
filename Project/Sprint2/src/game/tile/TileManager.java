package game.tile;

import game.creature.Creature;
import game.event.EventListener;
import game.event.EventType;
import game.player.Player;
import game.tile.types.CaveTile;
import game.tile.types.VolcanoTile;
import misc.Settings;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The TileManager class manages the creation and management of different types of tiles in the game.
 */
public class TileManager implements EventListener {
    // Lists to store different types of tiles
    private final List<Tile> caveTileList;
    private final List<Tile> volcanoTileList;
    private final List<Tile> gameTileList;

    // Path to the directory containing creature images
    private static final String CREATURE_FILE_PATH = "Project/Sprint2/src/resources/assets/pngs/creatures/";

    /**
     * Constructs a TileManager object.
     */
    public TileManager() {
        caveTileList = new ArrayList<>();
        volcanoTileList = new ArrayList<>();
        gameTileList = new ArrayList<>();
    }

    /**
     * Creates cave tiles based on the given creature list and player list.
     *
     * @param creatureList The list of creatures.
     * @param playerList   The list of players.
     */
    public void createCaveTileList(List<Creature> creatureList, List<Player> playerList) {
        // Used to calculate the cave tile indices.
        long boardSize = (long) Settings.getSetting("VolcanoTile");
        long humanPlayers = (long) Settings.getSetting("HumanPlayers");
        long AIPlayers = (long) Settings.getSetting("AIPlayers");

        int totalPlayers = (int) (humanPlayers + AIPlayers);
        int playerDistance = (int) (boardSize / totalPlayers);

        // Create as many caves as the number of players
        for (int i = 0; i < playerList.size(); i++){
            Creature currCreature = this.getTileableCreature(creatureList, i);

            String imagePath = CREATURE_FILE_PATH + currCreature.getCreatureName() + "_1.png";
            ImageIcon caveTileImage = new ImageIcon(imagePath);

            int currTileIndex = -1 * ((i * playerDistance) + 1);
            Player currPlayer = playerList.get(i);

            CaveTile caveTile = new CaveTile(caveTileImage, currTileIndex, currCreature, currPlayer);

            this.caveTileList.add(caveTile);
            this.gameTileList.add(caveTile);
        }
    }

    /**
     * Creates volcano tiles based on the given creature list.
     *
     * @param creatureList The list of creatures.
     */
    public void createVolcanoTileList(List<Creature> creatureList) {
        long boardSize = (long) Settings.getSetting("VolcanoTile");

        for (int i = 0; i < boardSize; i++){
            Creature currCreature = this.getTileableCreature(creatureList, i);

            String imagePath = CREATURE_FILE_PATH + currCreature.getCreatureName() + "_1.png";
            ImageIcon volcanoTileImage = new ImageIcon(imagePath);

            VolcanoTile volcanoTile = new VolcanoTile(volcanoTileImage, i, currCreature);

            this.volcanoTileList.add(volcanoTile);
            this.gameTileList.add(volcanoTile);
        }
    }

    /**
     * Helper method to get a tileable creature from the creature list.
     *
     * @param creatureList    The list of creatures.
     * @param currIteration  The current iteration.
     * @return               A tileable creature.
     */
    private Creature getTileableCreature(List<Creature> creatureList, int currIteration){
        // Keep looping until we get a creature that is tileable.
        Creature currCreature;
        int j = 0;

        do {
            currCreature = creatureList.get((currIteration + j) % creatureList.size());
            j++;

        } while (!currCreature.isTileable());

        return currCreature;
    }

    /**
     * Retrieves the list of cave tiles.
     *
     * @return The list of cave tiles.
     */
    public List<Tile> getCaveTileList() {
        return caveTileList;
    }

    /**
     * Retrieves the list of volcano tiles.
     *
     * @return The list of volcano tiles.
     */
    public List<Tile> getVolcanoTileList() {
        return volcanoTileList;
    }

    /**
     * Retrieves the list of all game tiles.
     *
     * @return The list of all game tiles.
     */
    public List<Tile> getGameTileList() {
        return gameTileList;
    }

    @Override
    public void update(EventType eventType) {
        // Placeholder for event handling logic
    }
}
