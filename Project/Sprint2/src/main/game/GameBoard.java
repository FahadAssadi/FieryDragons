package main.game;

import main.game.chit.DragonCard;
import main.game.chit.DragonCardFactory;
import main.game.chit.DragonCardManager;
import main.game.creature.Creature;
import main.game.creature.CreatureFactory;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.Player;
import main.game.player.PlayerManager;
import main.game.tile.TileManager;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameBoard class represents the game board and manages game-related entities.
 * It initializes players, tiles, and dragon cards, and handles player turns.
 */
public class GameBoard implements EventListener {
    // Manager for dragon cards
    private final DragonCardManager dragonCardManager;

    // Manager for players
    private final PlayerManager playerManager;

    // Manager for tiles
    private final TileManager tileManager;

    /**
     * Constructs a GameBoard object and initializes game-related entities.
     */
    public GameBoard() {
        // Create the Creatures list
        List<Creature> creatures = CreatureFactory.createCreatures();

        // Create the Players and the Player Queue
        this.playerManager = new PlayerManager();

        // Create the tiles
        this.tileManager = new TileManager();
        this.tileManager.createCaveTileList(creatures, this.playerManager.getPlayerList());
        this.tileManager.createVolcanoTileList(creatures);

        // usage of the dragonCard factory
        List<DragonCard> dragonCards = new ArrayList<>();;

        DragonCardFactory dragonCardFactory = new DragonCardFactory(creatures);
        while (dragonCardFactory.hasNext()){
            DragonCard card = dragonCardFactory.next();
            dragonCards.add(card);
        }


        // Create the Dragon Cards
        this.dragonCardManager = new DragonCardManager();
        this.dragonCardManager.setDragonCards(creatures);

        // Shuffle the Dragon Cards
        EventManager.getInstance().notify(EventType.SHUFFLE_CARDS);

        // Subscribe to player turn start events
        EventManager.getInstance().subscribe(EventType.PLAYER_TURN_START, this);
    }

    /**
     * Initiates the next player's turn.
     */
    public void nextPlayerTurn() {
        // Get the current player and the dragon card they've picked.
        Player currPlayer = this.playerManager.getCurrPlayer();
        DragonCard currDragonCard = this.dragonCardManager.getCurrDragonCard();

        // Execute the action associated with the dragon card.
        currDragonCard.getDragonCardCommand().execute(this, currPlayer);
    }

    /**
     * Handles event updates.
     *
     * @param eventType The type of event that occurred.
     */
    @Override
    public void update(EventType eventType) {
        // When the PLAYER_TURN_START event is triggered, run the nextPlayerTurn() method
        if (eventType == EventType.PLAYER_TURN_START) {
            this.nextPlayerTurn();
        }
    }

    // ----------- GETTERS only used by UI elements -----------
    // NO BACKEND ELEMENTS CAN ACCESS HIGH-LEVEL OBJECTS

    /**
     * Retrieves the dragon card manager.
     *
     * @return The dragon card manager.
     */
    public DragonCardManager getDragonCardManager() {
        return dragonCardManager;
    }

    /**
     * Retrieves the player manager.
     *
     * @return The player manager.
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    /**
     * Retrieves the tile manager.
     *
     * @return The tile manager.
     */
    public TileManager getTileManager() {
        return tileManager;
    }
}
