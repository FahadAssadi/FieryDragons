package game.player;

import game.event.EventListener;
import game.event.EventManager;
import game.event.EventType;
import game.player.behaviour.AIBehaviour;
import game.player.behaviour.HumanBehaviour;
import misc.Settings;
import misc.Utility;

import java.util.*;

public class PlayerManager implements EventListener {
    private final List<Player> playerList;
    private final Queue<Player> playerQueue;
    private Player currPlayer;
    
    private static final String DEFAULT_PLAYER_CONFIG_PATH = "Project/Sprint2/src/resources/data/players.json";

    public PlayerManager(){
        this.playerList = new ArrayList<>();
        this.playerQueue = new LinkedList<>();

        this.createPlayerList();

        for (Player player : this.playerList) {
            this.playerQueue.offer(player);
        }
        
        queueNextPlayer();

        EventManager.getInstance().subscribe(EventType.PLAYER_TURN_END, this);
    }

    public void queueNextPlayer(){
        this.currPlayer = this.playerQueue.poll();
        this.playerQueue.offer(this.currPlayer);
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

    public Player getCurrPlayer() {
        return this.currPlayer;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    @Override
    public void update(EventType eventType) {
        queueNextPlayer();
    }
}
