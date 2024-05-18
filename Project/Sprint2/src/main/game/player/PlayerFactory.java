package main.game.player;

import main.game.player.behaviour.AIBehaviour;
import main.game.player.behaviour.HumanBehaviour;
import main.misc.Settings;
import main.misc.Utility;

import java.util.Dictionary;
import java.util.Iterator;

public class PlayerFactory implements Iterator<Player> {
    private final int totalPlayers;
    private final long boardSize;
    private final long humanPlayers;
    private final long AIPlayers;
    private int currentIndex = 0;
    private static final String DEFAULT_PLAYER_CONFIG_PATH = "/data/players.json";

    public PlayerFactory() {
        boardSize = (long) Settings.getSetting("VolcanoTile");
        humanPlayers = (long) Settings.getSetting("HumanPlayers");
        AIPlayers = (long) Settings.getSetting("AIPlayers");
        totalPlayers = (int) (humanPlayers + AIPlayers);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < totalPlayers;
    }

    @Override
    public Player next() {
        int playerDistance = (int) (boardSize / totalPlayers);
        Dictionary playerConfig = Utility.getObjFromArr(Utility.readJSONFileToArr(getClass().getResourceAsStream(DEFAULT_PLAYER_CONFIG_PATH)), currentIndex);

        long playerID = (long) playerConfig.get("id");
        String playerColour = (String) playerConfig.get("colour");
        int playerStart = (int) playerID * playerDistance;

        Player newPlayer;
        if (currentIndex < humanPlayers) {
            newPlayer = new Player(new HumanBehaviour(), playerStart, playerColour);
        } else {
            newPlayer = new Player(new AIBehaviour(), playerStart, playerColour);
        }

        currentIndex++;
        return newPlayer;
    }
}