package main.game;

import main.game.chit.DragonCard;
import main.game.chit.DragonCardIterable;
import main.game.creature.CreatureKeeper;
import main.game.tile.TileKeeper;
import main.game.tile.TileNode;

import java.util.ArrayList;
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
        for (DragonCard dragonCard : dragonCardIterable) {
            dragonCardList.add(dragonCard);
            System.out.println(dragonCard);
        }

        test();
    }

    private void test(){
        for (int i = 0; i < 4; i++){
            TileNode currPlayerTile = this.tileKeeper.getPlayerTileIterator().next();
            System.out.println(currPlayerTile.getType().getPlayer());

            TileNode currVolcanoTile = currPlayerTile.getLeft();

            for (int j = 0; j < 6; j++){
                System.out.println(currVolcanoTile.getTempID());
                currVolcanoTile = currVolcanoTile.getLeft();
            }
        }
    }

}
