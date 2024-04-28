package com.fit3077.fierydragons.models.creatures;

import javafx.scene.image.Image;

public class Creature {
    private final String name;
    private final Image image;

    Creature (String name, String imagePath) {
        this.name = name;
        this.image = new Image(imagePath);
    }
    boolean compare(Creature other){
        return this == other;
    }

    public String getName() {
        return name;
    }
    /*
    You use it by creating a Creature dragon = new Creature("Dragon", "resources/dragon.png");
    ImageView imageView = new ImageView(dragon.getImage());
     */
    public Image getImage() {
        return image;
    }
}
