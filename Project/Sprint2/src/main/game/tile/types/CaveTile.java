package main.game.tile.types;

import main.game.creature.Creature;
import main.game.player.Player;
import main.game.tile.Tile;

import javax.swing.*;

/**
 * The CaveTile class represents a cave tile in the main.game.
 * It extends the Tile class and adds functionality specific to cave tiles.
 */
public class CaveTile extends Tile {

    /**
     * Constructs a CaveTile object with the specified image icon, tile index, creature, and player.
     *
     * @param imageIcon  The image icon representing the cave tile.
     * @param tileIndex  The index of the tile on the main.game board.
     * @param creature   The creature associated with the cave tile.
     * @param player     The player occupying the cave tile.
     */
    public CaveTile(ImageIcon imageIcon, int tileIndex, Creature creature, Player player) {
        super(imageIcon, tileIndex, creature);
        setPlayer(player);
    }
}
