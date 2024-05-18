package main.game.command.type.chit;

import main.game.chit.type.PirateDragonCard;

public class PirateDragonCardCommand extends DragonCardCommand{
    private final PirateDragonCard pirateDragonCard;

    public PirateDragonCardCommand(PirateDragonCard pirateDragonCard) {
        this.pirateDragonCard = pirateDragonCard;
    }

    @Override
    public void execute() {

    }
}
