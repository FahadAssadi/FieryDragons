package main.game.tile;

import main.game.creature.iterators.TileableCreatureIterable;
import main.game.tile.iterators.PlayerTileIterator;
import main.game.tile.iterators.VolcanoTileIterable;

public class TileKeeper {
    private final VolcanoTileIterable volcanoTileIterable;
    private final PlayerTileIterator playerTileIterator;

    public TileKeeper(TileableCreatureIterable tileableCreatureIterable) {
        this.volcanoTileIterable = new VolcanoTileIterable(tileableCreatureIterable);
        this.playerTileIterator = new PlayerTileIterator(tileableCreatureIterable, volcanoTileIterable);
    }

    public VolcanoTileIterable getVolcanoTileIterable() {
        return this.volcanoTileIterable;
    }

    public PlayerTileIterator getPlayerTileIterator() {
        return playerTileIterator;
    }
}
