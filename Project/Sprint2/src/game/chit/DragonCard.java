package game.chit;

import game.chit.command.DragonCardCommand;

import javax.swing.*;

public abstract class DragonCard {
    private final ImageIcon dragonCardImage;

    public DragonCard(ImageIcon dragonCardImage) {
        this.dragonCardImage = dragonCardImage;
    }

    public abstract DragonCardCommand getDragonCardCommand();

    public ImageIcon getDragonCardImage() {
        return dragonCardImage;
    }
}
