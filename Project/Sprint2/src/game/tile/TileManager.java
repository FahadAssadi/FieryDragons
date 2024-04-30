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

public class TileManager implements EventListener {
    private final List<Tile> caveTileList;
    private final List<Tile> volcanoTileList;
    private final List<Tile> gameTileList;

    private static final String volcanoTilePath = "Project/Sprint2/src/resources/assets/pngs/creatures/";

    public TileManager() {
        caveTileList = new ArrayList<>();
        volcanoTileList = new ArrayList<>();
        gameTileList = new ArrayList<>();
    }

    public void createCaveTileList(List<Creature> creatureList, List<Player> playerList) {
        // Used to calculate the cave tile indices.
        long boardSize = (long) Settings.getSetting("VolcanoTile");
        long humanPlayers = (long) Settings.getSetting("HumanPlayers");
        long AIPlayers = (long) Settings.getSetting("AIPlayers");

        int totalPlayers = (int) (humanPlayers + AIPlayers);
        int playerDistance = (int) (boardSize / totalPlayers);

        String caveFilePath = "Project/Sprint2/src/resources/assets/pngs/tiles/Cave.png";

        // Create as many caves as the number of players
        for (int i = 0; i < playerList.size(); i++){
            ImageIcon caveTileImage = new ImageIcon(caveFilePath);
            int currTileIndex = -1 * ((i * playerDistance) + 1);
            Creature currCreature = this.getTileableCreature(creatureList, i);
            Player currPlayer = playerList.get(i);

            CaveTile caveTile = new CaveTile(caveTileImage, currTileIndex, currCreature, currPlayer);

            this.caveTileList.add(caveTile);
            this.gameTileList.add(caveTile);
        }
    }

    public void createVolcanoTileList(List<Creature> creatureList) {
        long boardSize = (long) Settings.getSetting("VolcanoTile");

        for (int i = 0; i < boardSize; i++){
            Creature currCreature = this.getTileableCreature(creatureList, i);

            String imagePath = volcanoTilePath + currCreature.getCreatureName() + "_1.png";
            ImageIcon volcanoTileImage = new ImageIcon(imagePath);

            VolcanoTile volcanoTile = new VolcanoTile(volcanoTileImage, i, currCreature);

            this.volcanoTileList.add(volcanoTile);
            this.gameTileList.add(volcanoTile);
        }
    }

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

    public List<Tile> getCaveTileList() {
        return caveTileList;
    }

    public List<Tile> getVolcanoTileList() {
        return volcanoTileList;
    }

    public List<Tile> getGameTileList() {
        return gameTileList;
    }

    @Override
    public void update(EventType eventType) {

    }
}
