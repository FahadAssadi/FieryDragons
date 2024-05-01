package game.chit.command;

import game.GameBoard;
import game.player.Player;

/**
 * The DragonCardCommand abstract class represents a command associated with a dragon card in the game.
 * This class provides a blueprint for specific types of commands related to dragon cards.
 */
public abstract class DragonCardCommand {
    /**
     * Executes the command associated with the dragon card.
     *
     * @param gameBoard The game board where the command is executed.
     * @param player    The player who triggers the command.
     */
    public abstract void execute(GameBoard gameBoard, Player player);
}
