package com.fit3077.fierydragons.models.creatures;

public class Creature {
    private final String name;
    private final String imagePath;

    Creature (String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }
    boolean compare(Creature other){
        return this == other;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}
