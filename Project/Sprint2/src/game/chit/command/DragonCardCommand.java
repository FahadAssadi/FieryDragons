package game.chit.command;

import game.chit.DragonCard;
import game.player.Player;

public abstract class DragonCardCommand {
    private final DragonCard dragonCard;

    public DragonCardCommand(DragonCard dragonCard) {
        this.dragonCard = dragonCard;
    }

    public abstract void execute(Player player);
}
