package main.game;

import main.game.chit.DragonCard;
import main.game.chit.DragonCardKeeper;
import main.game.creature.CreatureKeeper;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.tile.TileKeeper;

/**
 * The GameBoard class represents the game board and manages game-related entities.
 * It initializes players, tiles, and dragon cards, and handles player turns.
 */
public class GameBoard implements EventListener {
    private final DragonCardKeeper dragonCardKeeper;
    private final TileKeeper tileKeeper;

    /**
     * Constructs a GameBoard object and initializes game-related entities.
     */
    public GameBoard() {
        CreatureKeeper creatureKeeper = new CreatureKeeper();
        this.tileKeeper = new TileKeeper(creatureKeeper.getTileableCreatureIterable());
        this.dragonCardKeeper = new DragonCardKeeper(creatureKeeper.getCreatureIterable());

        EventManager.getInstance().subscribe(EventType.PLAYER_TURN_START, this);
    }

    public TileKeeper getTileKeeper() {
        return tileKeeper;
    }

    public DragonCardKeeper getDragonCardKeeper() {
        return dragonCardKeeper;
    }

    public void nextPlayerTurn() {
        DragonCard currDragonCard = this.dragonCardKeeper.getSelectedDragonCard();

        currDragonCard.getDragonCardCommand().execute(this);
    }

    @Override
    public void update(EventType eventType) {
        if (eventType == EventType.PLAYER_TURN_START){
            this.nextPlayerTurn();
        }
    }
}
