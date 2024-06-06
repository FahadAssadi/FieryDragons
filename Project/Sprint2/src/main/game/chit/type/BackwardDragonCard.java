package main.game.chit.type;

import main.game.chit.DragonCard;
import main.game.chit.commands.BackwardsDragonCardCommand;
import main.game.chit.commands.DragonCardCommand;

import javax.swing.*;

public class BackwardDragonCard extends DragonCard {
    public BackwardDragonCard(ImageIcon dragonCardImage) {
        super(dragonCardImage);
    }
    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new BackwardsDragonCardCommand(this);
    }
}
