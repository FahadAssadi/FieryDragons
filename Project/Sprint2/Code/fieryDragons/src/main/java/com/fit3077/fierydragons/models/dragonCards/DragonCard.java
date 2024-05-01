package com.fit3077.fierydragons.models.dragonCards;

import com.fit3077.fierydragons.models.actions.Action;

public abstract class DragonCard implements Action{
    private final String imagePath;
    private final String name;
    DragonCard(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }
    abstract Action getAction();
    public String getImagePath(){
        return imagePath;
    }

    public String getName() {
        return name;
    }
}
