package game.tile;

import game.creature.Creature;
import game.tile.types.VolcanoTile;
import misc.Settings;

import java.util.ArrayList;
import java.util.List;

public class TileManager {
    private List<Tile> caveTileList;
    private List<Tile> volcanoTileList;
    private List<Tile> gameTileList;

    public TileManager() {
        caveTileList = new ArrayList<>();
        volcanoTileList = new ArrayList<>();
        gameTileList = new ArrayList<>();
    }

    public void setCreatureTileList(List<Creature> creatureList){
        long boardSize = (long) Settings.getSetting("VolcanoTile");
        long humanPlayers = (long) Settings.getSetting("HumanPlayers");
        long AIPlayers = (long) Settings.getSetting("AIPlayers");

        int totalPlayers = (int) (humanPlayers + AIPlayers);

        int playerDistance = (int) (boardSize / totalPlayers);

        for (int i = 0; i < totalPlayers; i++){

        }

    }
}
