package com.fit3077.fierydragons.models.board;

import com.fit3077.fierydragons.models.creatures.Creature;
import com.fit3077.fierydragons.models.player.Player;

public abstract class Tile {
    private Creature creature;
    private Player player;

    Tile (Creature creature, Player player) {
        this.creature = creature;
        this.player = player;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
