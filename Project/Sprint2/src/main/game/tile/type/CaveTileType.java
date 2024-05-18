package main.game.tile.type;

import main.game.creature.Creature;
import main.game.player.Player;

import javax.swing.*;

public class CaveTileType extends TileType{
    public CaveTileType(ImageIcon imageIcon, Creature creature, Player player) {
        super(imageIcon, creature);
        this.setPlayer(player);
    }
}
