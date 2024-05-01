package com.fit3077.fierydragons.models.dragonCards;

import com.fit3077.fierydragons.models.actions.Action;
import com.fit3077.fierydragons.models.creatures.Creature;

public class StandardDragonCard extends DragonCard {
    private int moves;
    private Creature creature;
    private final Action action;
    StandardDragonCard(Action action, int moves, Creature creature, String imagePath) {
        super(creature.getName(), imagePath);
        this.action = action;
        this.moves = moves;
        this.creature = creature;
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

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public void execute() {
        action.execute();
    }
}
