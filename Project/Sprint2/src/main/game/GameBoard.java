package main.game;

import main.game.chit.DragonCardKeeper;
import main.game.creature.CreatureKeeper;
import main.game.snapshot.Memento;
import main.game.tile.TileKeeper;

import java.util.HashMap;
import java.util.Map;


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

    /**
     * Overrides the save method to save the current state of the game board.
     *
     * @param  map  the map to save the state to
     * @return      the updated map with the saved state
     */
    @Override
    public Map save(Map map) {
        map.put("tileKeeper", this.tileKeeper.save(map));
        map.put("dragonCardKeeper", this.dragonCardKeeper.save(map));
        return map;
    }

    @Override
    public Map load(Map map) {
        return Map.of();
    }
}
