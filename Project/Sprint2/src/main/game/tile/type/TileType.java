package main.game.tile.type;

import main.game.creature.Creature;
import main.game.player.Player;

import javax.swing.*;

public abstract class TileType {
    private final ImageIcon imageIcon;
    private final Creature creature;
    private Player player;

    public TileType(ImageIcon imageIcon, Creature creature) {
        this.imageIcon = imageIcon;
        this.creature = creature;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public Creature getCreature() {
        return creature;
    }

    public Player getPlayer() {
        return player;
    }

}
