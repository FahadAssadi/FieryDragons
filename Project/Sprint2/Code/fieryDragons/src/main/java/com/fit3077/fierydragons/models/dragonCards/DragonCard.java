package com.fit3077.fierydragons.models.dragonCards;

import com.fit3077.fierydragons.models.actions.Action;

public abstract class DragonCard {
    private final String imagePath;
    DragonCard(String imagePath) {
        this.imagePath = imagePath;
    }
    abstract Action getAction();
    public String getImagePath(){
        return imagePath;
    }
}
