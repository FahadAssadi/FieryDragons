package game.tile;

import game.creature.Creature;
import game.player.Player;

import javax.swing.*;

public abstract class Tile {
    private final ImageIcon imageIcon;
    private final int tileIndex;
    private final Creature creature;
    private Player player;

    public Tile(ImageIcon imageIcon, int tileIndex, Creature creature){
        this.imageIcon = imageIcon;
        this.tileIndex = tileIndex;
        this.creature = creature;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
