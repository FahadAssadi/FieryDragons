package main.game.tile;

import main.game.creature.iterators.TileableCreatureIterable;
import main.game.snapshot.Memento;
import main.game.tile.iterators.PlayerTileQueue;
import main.game.tile.iterators.VolcanoCardIterable;

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
    public Map save(Map map) {
        map.put("volcanoCardIterable", volcanoCardIterable.save(map));
        map.put("playerTileQueue", playerTileQueue.save(map));
        return map;
    }

    @Override
    public Map load(Map map) {
        return Map.of();
    }
}
