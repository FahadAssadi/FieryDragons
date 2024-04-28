package com.fit3077.fierydragons.models.board;

import com.fit3077.fierydragons.models.creatures.Creature;
import com.fit3077.fierydragons.models.player.Player;

public class Tile  {
    private Creature creature;
    private Player player;

    Tile (Creature creature) {
        this.creature = creature;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    boolean checkCollision() {
        return false;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
