package main.game;

import main.game.chit.DragonCardKeeper;
import main.game.creature.CreatureKeeper;
import main.game.snapshot.Memento;
import main.game.tile.TileKeeper;

/**
 * The GameBoard class represents the game board and manages game-related entities.
 * It initializes players, tiles, and dragon cards, and handles player turns.
 */
public class GameBoard implements Memento {
    private final DragonCardKeeper dragonCardKeeper;
    private final TileKeeper tileKeeper;

    /**
     * Constructs a GameBoard object and initializes game-related entities.
     */
    public GameBoard() {
        CreatureKeeper creatureKeeper = new CreatureKeeper();
        this.tileKeeper = new TileKeeper(creatureKeeper.getTileableCreatureIterable());
        this.dragonCardKeeper = new DragonCardKeeper(creatureKeeper.getCreatureIterable());
    }

    // Getters only used by UI elements and command classes
    public TileKeeper getTileKeeper() {
        return tileKeeper;
    }

    public DragonCardKeeper getDragonCardKeeper() {
        return dragonCardKeeper;
    }


    @Override
    public void save(String filePath) {

    }

    @Override
    public Object load(String filePath) {
        return null;
    }

}
