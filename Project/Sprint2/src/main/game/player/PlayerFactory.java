package main.game.player;

import main.game.player.behaviour.AIBehaviour;
import main.game.player.behaviour.HumanBehaviour;
import main.misc.Settings;
import main.misc.Utility;

import java.util.Dictionary;
import java.util.Iterator;

public class PlayerFactory implements Iterator<Player> {
    private final int totalPlayers;
    private final long humanPlayers;
    private final long AIPlayers;
    private int currentIndex = 0;
    private static final String DEFAULT_PLAYER_CONFIG_PATH = "/data/players.json";

    public PlayerFactory() {
        humanPlayers = (long) Settings.getSetting("HumanPlayers");
        AIPlayers = (long) Settings.getSetting("AIPlayers");
        totalPlayers = (int) (humanPlayers + AIPlayers);
    }

    /**
     * Checks if there are more players to iterate over.
     *
     * @return true if there are more players, false otherwise
     */
    @Override
    public boolean hasNext() {
        return currentIndex < totalPlayers;
    }

    /**
     * Returns the next player in the iteration.
     *
     * @return         	The next player in the iteration.
     */
    @Override
    public Player next() {
        Dictionary playerConfig = Utility.getObjFromArr(Utility.readJSONFileToArr(getClass().getResourceAsStream(DEFAULT_PLAYER_CONFIG_PATH)), currentIndex);

        String playerColour = (String) playerConfig.get("colour");

        Player newPlayer;
        if (currentIndex < humanPlayers) {
            newPlayer = new Player(new HumanBehaviour(), playerColour);
        } else {
            newPlayer = new Player(new AIBehaviour(), playerColour);
        }

        currentIndex++;
        return newPlayer;
    }
}