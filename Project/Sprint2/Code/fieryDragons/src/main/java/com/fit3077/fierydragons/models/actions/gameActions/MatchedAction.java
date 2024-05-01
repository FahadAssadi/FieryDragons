package com.fit3077.fierydragons.models.actions.gameActions;

import com.fit3077.fierydragons.models.dragonCards.DragonCardsManager;
import com.fit3077.fierydragons.models.event.Subscriber;

public class MatchedAction implements Subscriber {
    DragonCardsManager dragonCardsManager;
    public MatchedAction(DragonCardsManager dragonCardsManager){
        this.dragonCardsManager = dragonCardsManager;
    }
    @Override
    public void update() {
        // run the execute method of the dragon card action whatever it may be.
        dragonCardsManager.getDragonCard().execute();
    }
}
