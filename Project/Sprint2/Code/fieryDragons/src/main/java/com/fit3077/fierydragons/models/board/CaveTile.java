package com.fit3077.fierydragons.models.board;

import com.fit3077.fierydragons.models.creatures.Creature;
import com.fit3077.fierydragons.models.player.Player;

public class CaveTile extends Tile{
    CaveTile(Creature creature, Player player) {
        super(creature, player);
    }
}
