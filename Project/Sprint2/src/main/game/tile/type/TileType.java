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

    /**
     * Sets the player for this TileType.
     *
     * @param  player  the player to be set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Returns the ImageIcon associated with this TileType.
     *
     * @return the ImageIcon representing this TileType
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Returns the Creature associated with this TileType.
     *
     * @return the Creature representing this TileType
     */
    public Creature getCreature() {
        return creature;
    }

    /**
     * Returns the Player associated with this TileType.
     *
     * @return the Player representing this TileType
     */
    public Player getPlayer() {
        return player;
    }

}
