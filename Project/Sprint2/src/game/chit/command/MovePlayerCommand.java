package game.chit.command;

import game.chit.DragonCard;
import game.player.Player;

public class MovePlayerCommand extends DragonCardCommand{


    public MovePlayerCommand(DragonCard dragonCard) {
        super(dragonCard);
    }

    @Override
    public void execute(Player player) {

    }
}
