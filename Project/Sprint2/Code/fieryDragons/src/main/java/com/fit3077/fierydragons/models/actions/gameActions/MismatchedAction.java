package com.fit3077.fierydragons.models.actions.gameActions;

import com.fit3077.fierydragons.models.event.Subscriber;
import com.fit3077.fierydragons.models.player.PlayerManager;

public class MismatchedAction implements Subscriber {
    PlayerManager playerManager;
    public MismatchedAction(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

    @Override
    public void update() {
        // When mismatched, go to next player
        playerManager.nextPlayer();
    }
}
