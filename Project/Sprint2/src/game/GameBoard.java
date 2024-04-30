package game;

import game.chit.DragonCard;
import game.chit.DragonCardManager;
import game.creature.Creature;
import game.creature.CreatureFactory;
import game.event.EventListener;
import game.event.EventManager;
import game.event.EventType;
import game.player.Player;
import game.player.PlayerManager;
import game.tile.TileManager;

import java.util.List;

public class GameBoard implements EventListener {
    private final DragonCardManager dragonCardManager;
    private final PlayerManager playerManager;
    private final TileManager tileManager;

    public GameBoard() {
        // Create the Creatures list
        List<Creature> creatures = CreatureFactory.createCreatures();

        // Create the Players and the Player Queue
        this.playerManager = new PlayerManager();

        // Create the tiles
        this.tileManager = new TileManager();
        this.tileManager.createCaveTileList(creatures, this.playerManager.getPlayerList());
        this.tileManager.createVolcanoTileList(creatures);

        // Create the Dragon Cards
        this.dragonCardManager = new DragonCardManager();
        this.dragonCardManager.setDragonCards(creatures);

        //Shuffle the Dragon Cards
        this.dragonCardManager.shuffleDragonCards();

        EventManager.getInstance().subscribe(EventType.PLAYER_TURN_START, this);
    }

    public void nextPlayerTurn(){
        Player currPlayer = this.playerManager.getCurrPlayer();
        DragonCard currDragonCard = this.dragonCardManager.getCurrDragonCard();

        currDragonCard.getDragonCardCommand().execute(currPlayer);
    }

    @Override
    public void update(EventType eventType) {
        this.nextPlayerTurn();
    }

    // ----------- GETTERS only used by UI elements -----------
    // NO BACKEND ELEMENTS CAN ACCESS HIGH LEVEL OBJECTS

    public DragonCardManager getDragonCardManager() {
        return dragonCardManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
