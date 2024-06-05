package main.game.tile;

import main.game.creature.iterators.TileableCreatureIterable;
import main.game.tile.iterators.PlayerTileQueue;
import main.game.tile.iterators.VolcanoCardIterable;
import main.game.tile.iterators.VolcanoTileIterable;

public class TileKeeper {
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
}
