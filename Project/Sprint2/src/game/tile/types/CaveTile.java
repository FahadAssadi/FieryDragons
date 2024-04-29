package game.tile.types;

import game.creature.Creature;
import game.player.Player;
import game.tile.Tile;

import javax.swing.*;

public class CaveTile extends Tile {

    public CaveTile(ImageIcon imageIcon, int tileIndex, Creature creature, Player player) {
        super(imageIcon, tileIndex, creature);
        setPlayer(player);
    }
}
