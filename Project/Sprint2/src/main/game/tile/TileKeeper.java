package main.game.tile;

import main.game.creature.iterators.TileableCreatureIterable;
import main.game.snapshot.Memento;
import main.game.tile.iterators.PlayerTileQueue;
import main.game.tile.iterators.VolcanoCardIterable;

import java.util.LinkedHashMap;
import java.util.Map;

public class TileKeeper implements Memento {
    private final VolcanoCardIterable volcanoCardIterable;
    private final PlayerTileQueue playerTileQueue;

    public TileKeeper(TileableCreatureIterable tileableCreatureIterable) {
        this.volcanoCardIterable = new VolcanoCardIterable(tileableCreatureIterable);
        this.playerTileQueue = new PlayerTileQueue(tileableCreatureIterable, volcanoCardIterable);
    }

    public TileKeeper(Map<String , Object> saveMap, TileableCreatureIterable tileableCreatureIterable) {

        Map<String, Object> volcanoCardIterableSaveMap = (Map<String, Object>) saveMap.get("volcanoCardIterable");
        this.volcanoCardIterable = new VolcanoCardIterable(volcanoCardIterableSaveMap, tileableCreatureIterable);

        Map<String, Object> playerTileQueueSaveMap = (Map<String, Object>) saveMap.get("Players");
        this.playerTileQueue = new PlayerTileQueue(playerTileQueueSaveMap, tileableCreatureIterable, volcanoCardIterable);
    }

    /**
     * Returns the VolcanoCardIterable object that is a member of this TileKeeper.
     *
     * @return  the VolcanoCardIterable object
     */
    public VolcanoCardIterable getVolcanoCardIterable() {
        return this.volcanoCardIterable;
    }

    /**
     * Returns the PlayerTileQueue object that is a member of this TileKeeper.
     *
     * @return  the PlayerTileQueue object
     */
    public PlayerTileQueue getPlayerTileQueue() {
        return playerTileQueue;
    }

    /**
     * Overrides the save method to save the state of the TileKeeper object.
     *
     * @param  map  a map containing the state of the TileKeeper object
     * @return      a map containing the updated state of the TileKeeper object
     */
    @Override
    public Map<String , Object> save(Map<String , Object> map) {
        map.put("volcanoCardIterable", volcanoCardIterable.save(new LinkedHashMap<>()));
        map.put("Players", playerTileQueue.save(new LinkedHashMap<>()));
        return map;
    }

}
