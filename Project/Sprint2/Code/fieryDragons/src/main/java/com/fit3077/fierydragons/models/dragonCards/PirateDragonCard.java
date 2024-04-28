package com.fit3077.fierydragons.models.dragonCards;

import com.fit3077.fierydragons.models.actions.CardAction;
import com.fit3077.fierydragons.models.creatures.Creature;

public class PirateDragonCard extends DragonCard {
    private int moves;
    private Creature creature;
    PirateDragonCard(CardAction action) {
        super(action);
    }
    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

}
