package main.game;

import main.game.chit.DragonCardKeeper;
import main.game.creature.CreatureKeeper;
import main.game.snapshot.Memento;
import main.game.tile.TileKeeper;
import main.misc.Utility;

import java.util.LinkedHashMap;
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
    public Map<String , Object> save(Map<String , Object> map) {
        map.put("dragonCardKeeper", this.dragonCardKeeper.save(new LinkedHashMap<>()));
        map.put("tileKeeper", this.tileKeeper.save(new LinkedHashMap<>()));
        return map;
    }


    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        Map<String, Object> map = gameBoard.save(new LinkedHashMap<>());

        String path = "save_" + 10 + ".yml";
        Utility.writeYamlFile(map, path);
    }

}
