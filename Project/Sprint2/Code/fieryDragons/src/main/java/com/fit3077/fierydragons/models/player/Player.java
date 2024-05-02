package com.fit3077.fierydragons.models.player;

import com.fit3077.fierydragons.models.actions.Action;

public abstract class Player {
    private String playerName;
    private final String playerImage;

    public Player(String playerName, String playerImage) {
        this.playerName = playerName;
        this.playerImage = playerImage;
    }

    public String getPlayerImage() {
        return playerImage;
    }

    public String getPlayerName() {
        return playerName;
    }
}
