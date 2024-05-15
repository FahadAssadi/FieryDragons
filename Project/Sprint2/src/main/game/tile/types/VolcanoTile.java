package main.game.tile.types;

import main.game.creature.Creature;
import main.game.tile.Tile;

import javax.swing.*;

/**
 * The VolcanoTile class represents a volcano tile in the main.game.
 * It extends the Tile class and adds functionality specific to volcano tiles.
 */
public class VolcanoTile extends Tile {

    /**
     * Constructs a VolcanoTile object with the specified image icon, tile index, and creature.
     *
     * @param imageIcon The image icon representing the volcano tile.
     * @param tileIndex The index of the tile on the main.game board.
     * @param creature  The creature associated with the volcano tile.
     */
    public VolcanoTile(ImageIcon imageIcon, int tileIndex, Creature creature) {
        super(imageIcon, tileIndex, creature);
    }
}
