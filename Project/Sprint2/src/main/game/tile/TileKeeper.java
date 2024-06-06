package main.game.tile;

import main.game.creature.iterators.TileableCreatureIterable;
import main.game.snapshot.Memento;
import main.game.tile.iterators.PlayerTileQueue;
import main.game.tile.iterators.VolcanoCardIterable;

import java.util.HashMap;
import java.util.Map;

public class TileKeeper implements Memento {
    private final VolcanoCardIterable volcanoCardIterable;
    private final PlayerTileQueue playerTileQueue;

    public TileKeeper(TileableCreatureIterable tileableCreatureIterable) {
        this.volcanoCardIterable = new VolcanoCardIterable(tileableCreatureIterable);
        this.playerTileQueue = new PlayerTileQueue(tileableCreatureIterable, volcanoCardIterable);
    }

    public VolcanoCardIterable getVolcanoCardIterable() {
        return this.volcanoCardIterable;
    }

    public PlayerTileQueue getPlayerTileQueue() {
        return playerTileQueue;
    }

    @Override
    public Map<String , Object> save(Map<String , Object> map) {
        map.put("volcanoCardIterable", volcanoCardIterable.save(map));
        map.put("playerTileQueue", playerTileQueue.save(new HashMap<>()));
        return map;
    }

    @Override
    public Map<String , Object> load(Map map) {
        return Map.of();
    }
}
