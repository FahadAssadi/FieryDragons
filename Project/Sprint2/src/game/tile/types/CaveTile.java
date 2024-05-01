package game.tile.types;

import game.creature.Creature;
import game.player.Player;
import game.tile.Tile;

import javax.swing.*;

/**
 * The CaveTile class represents a cave tile in the game.
 * It extends the Tile class and adds functionality specific to cave tiles.
 */
public class CaveTile extends Tile {

    /**
     * Constructs a CaveTile object with the specified image icon, tile index, creature, and player.
     *
     * @param imageIcon  The image icon representing the cave tile.
     * @param tileIndex  The index of the tile on the game board.
     * @param creature   The creature associated with the cave tile.
     * @param player     The player occupying the cave tile.
     */
    public CaveTile(ImageIcon imageIcon, int tileIndex, Creature creature, Player player) {
        super(imageIcon, tileIndex, creature);
        setPlayer(player);
    }
}
