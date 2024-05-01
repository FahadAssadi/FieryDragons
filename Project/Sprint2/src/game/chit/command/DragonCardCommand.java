package game.chit.command;

import game.GameBoard;
import game.player.Player;

public abstract class DragonCardCommand {

    public abstract void execute(GameBoard gameBoard, Player player);
}
