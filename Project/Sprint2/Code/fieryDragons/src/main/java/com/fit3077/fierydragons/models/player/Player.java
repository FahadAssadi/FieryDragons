package com.fit3077.fierydragons.models.player;

import com.fit3077.fierydragons.models.actions.Action;

public class Player {
    private String playerName;
    private String playerImage = "imgs/HiddenChit.png";

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerImage() {
        return playerImage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
