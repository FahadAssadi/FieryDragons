package game.player;

import game.player.behaviour.AIBehaviour;
import game.player.behaviour.HumanBehaviour;
import misc.Settings;
import misc.Utility;

import java.util.*;

public class PlayerManager {
    private final List<Player> playerList = new ArrayList<>();
    private final Queue<Player> playerQueue = new LinkedList<>();
    private static final String DEFAULT_PLAYER_CONFIG_PATH = "Project/Sprint2/src/resources/data/players.json";

    public PlayerManager(){

    }

    private void createPlayerList(){
        long boardSize = (long) Settings.getSetting("VolcanoTile");
        long humanPlayers = (long) Settings.getSetting("HumanPlayers");
        long AIPlayers = (long) Settings.getSetting("AIPlayers");

        int totalPlayers = (int) (humanPlayers + AIPlayers);

        int playerDistance = (int) (boardSize / totalPlayers);

        for (int i = 0; i < totalPlayers; i++){
            Dictionary playerConfig = Utility.getObjFromArr(Utility.readJSONFileToArr(DEFAULT_PLAYER_CONFIG_PATH), i);

            long playerID = (long) playerConfig.get("id");
            String playerColour = (String) playerConfig.get("colour");

            int playerStart = (int) playerID * playerDistance;
            Player newPlayer;

            if (i < humanPlayers){
                newPlayer = new Player(new HumanBehaviour(), playerStart, playerColour);
            } else {
                newPlayer = new Player(new AIBehaviour(), playerStart, playerColour);
            }

            this.playerList.add(newPlayer);
        }

    }
}
