package main.game;

import main.game.chit.DragonCard;
import main.game.chit.DragonCardIterable;
import main.game.creature.CreatureKeeper;
import main.game.tile.TileKeeper;
import main.game.tile.TileNode;
import main.misc.Settings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The GameBoard class represents the game board and manages game-related entities.
 * It initializes players, tiles, and dragon cards, and handles player turns.
 */
public class GameBoard {
    private final TileKeeper tileKeeper;

    /**
     * Constructs a GameBoard object and initializes game-related entities.
     */
    public GameBoard() {
        CreatureKeeper creatureKeeper = new CreatureKeeper();
        this.tileKeeper = new TileKeeper(creatureKeeper.getTileableCreatureIterable());

        DragonCardIterable dragonCardIterable = new DragonCardIterable(creatureKeeper.getCreatureIterable());

        List<DragonCard> dragonCardList = new ArrayList<>();


        test();
    }

    public TileKeeper getTileKeeper() {
        return tileKeeper;
    }

    private void test(){
        int playerDistance = (int) Settings.getSetting("PlayerDistance");
        int numTiles = (int) Settings.getSetting("TotalPlayers");

        Iterator<TileNode> volcanoTileIterator = this.tileKeeper.getVolcanoTileIterable().iterator();

        for (int i = 0; i < numTiles; i++){
            TileNode caveTileNode = volcanoTileIterator.next().getRight();
            System.out.println(caveTileNode.getType().getPlayer());

            for (int j = 1; j < playerDistance; j++){
                volcanoTileIterator.next();
            }
        }
    }

}
