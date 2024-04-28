package com.fit3077.fierydragons.models.dragonCards;

import com.fit3077.fierydragons.models.actions.CardAction;

public abstract class DragonCard {
    CardAction action;
    DragonCard(CardAction action) {
        this.action = action;
    }
    void setAction(CardAction action) {
        this.action = action;
    };
    CardAction getAction(){
        return this.action;
    };
}
