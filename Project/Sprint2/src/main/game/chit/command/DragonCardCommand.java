package main.game.chit.command;

import main.game.GameBoard;
import main.game.player.Player;

/**
 * The DragonCardCommand abstract class represents a command associated with a dragon card in the main.game.
 * This class provides a blueprint for specific types of commands related to dragon cards.
 */
public abstract class DragonCardCommand {
    /**
     * Executes the command associated with the dragon card.
     *
     * @param gameBoard The main.game board where the command is executed.
     * @param player    The player who triggers the command.
     */
    public abstract void execute(GameBoard gameBoard, Player player);
}
