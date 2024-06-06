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

    public GameBoard(Map<String, Object> saveMap) {
        CreatureKeeper creatureKeeper = new CreatureKeeper();

        Map<String, Object> dragonKeeperSaveMap = (Map<String, Object>) saveMap.get("dragonCardKeeper");
        this.dragonCardKeeper = new DragonCardKeeper(dragonKeeperSaveMap, creatureKeeper.getCreatureIterable());

        Map<String, Object> tileKeeperSaveMap = (Map<String, Object>) saveMap.get("tileKeeper");
        this.tileKeeper = new TileKeeper(tileKeeperSaveMap, creatureKeeper.getTileableCreatureIterable());
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
        Map<String, Object> map = Utility.readYamlFile("save_10.yml");

        GameBoard gameBoard = new GameBoard(map);
    }

}
