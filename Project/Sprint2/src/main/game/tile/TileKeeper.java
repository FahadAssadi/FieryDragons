package main.game.tile;

import main.game.creature.iterators.TileableCreatureIterable;
import main.game.tile.iterators.PlayerTileQueue;
import main.game.tile.iterators.VolcanoTileIterable;

public class TileKeeper {
    private final VolcanoTileIterable volcanoTileIterable;
    private final PlayerTileQueue playerTileQueue;

    public TileKeeper(TileableCreatureIterable tileableCreatureIterable) {
        this.volcanoTileIterable = new VolcanoTileIterable(tileableCreatureIterable);
        this.playerTileQueue = new PlayerTileQueue(tileableCreatureIterable, volcanoTileIterable);
    }

    public VolcanoTileIterable getVolcanoTileIterable() {
        return this.volcanoTileIterable;
    }

    public PlayerTileQueue getPlayerTileQueue() {
        return playerTileQueue;
    }
}
