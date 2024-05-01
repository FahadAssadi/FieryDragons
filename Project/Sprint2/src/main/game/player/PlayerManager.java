package main.game.player;

import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.behaviour.AIBehaviour;
import main.game.player.behaviour.HumanBehaviour;
import main.misc.Settings;
import main.misc.Utility;

import java.util.*;

/**
 * The PlayerManager class manages players in the main.game.
 * It handles the creation of player instances, maintains a queue of players, and manages player turns.
 */
public class PlayerManager implements EventListener {
    // List to store all players
    private final List<Player> playerList;

    // Queue to maintain the order of player turns
    private final Queue<Player> playerQueue;

    // Currently active player
    private Player currPlayer;

    // Default path for player configuration file
    private static final String DEFAULT_PLAYER_CONFIG_PATH = "/data/players.json";

    /**
     * Constructs a PlayerManager object.
     * Creates player instances, initializes the player queue, and subscribes to player turn events.
     */
    public PlayerManager() {
        this.playerList = new ArrayList<>();
        this.playerQueue = new LinkedList<>();
        this.createPlayerList();

        // Initialize player queue with players
        for (Player player : this.playerList) {
            this.playerQueue.offer(player);
        }

        // Set the first player as the current player
        queueNextPlayer();

        // Subscribe to player turn end events
        EventManager.getInstance().subscribe(EventType.PLAYER_TURN_END, this);
    }

    /**
     * Queues the next player in the player queue.
     */
    public void queueNextPlayer() {
        this.currPlayer = this.playerQueue.poll();
        this.playerQueue.offer(this.currPlayer);
    }

    /**
     * Creates player instances based on settings and player configuration.
     */
    private void createPlayerList() {
        long boardSize = (long) Settings.getSetting("VolcanoTile");
        long humanPlayers = (long) Settings.getSetting("HumanPlayers");
        long AIPlayers = (long) Settings.getSetting("AIPlayers");

        int totalPlayers = (int) (humanPlayers + AIPlayers);
        int playerDistance = (int) (boardSize / totalPlayers);

        for (int i = 0; i < totalPlayers; i++) {
            // Read player configuration from file
            Dictionary playerConfig = Utility.getObjFromArr(Utility.readJSONFileToArr(getClass().getResourceAsStream(DEFAULT_PLAYER_CONFIG_PATH)), i);

            // Extract player ID and colour from configuration
            long playerID = (long) playerConfig.get("id");
            String playerColour = (String) playerConfig.get("colour");

            // Calculate starting position based on player ID and board size
            int playerStart = (int) playerID * playerDistance;

            // Create new player instance with appropriate behaviour (human or AI)
            Player newPlayer;
            if (i < humanPlayers) {
                newPlayer = new Player(new HumanBehaviour(), playerStart, playerColour);
            } else {
                newPlayer = new Player(new AIBehaviour(), playerStart, playerColour);
            }

            // Add player to the player list
            this.playerList.add(newPlayer);
        }
    }

    /**
     * Retrieves the currently active player.
     *
     * @return The currently active player.
     */
    public Player getCurrPlayer() {
        return this.currPlayer;
    }

    /**
     * Retrieves the list of players.
     *
     * @return The list of players.
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Updates the player manager in response to player turn end events.
     * Queues the next player in turn.
     *
     * @param eventType The type of event that occurred.
     */
    @Override
    public void update(EventType eventType) {
        queueNextPlayer();
    }
}
