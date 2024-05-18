package main.game.command.type.chit;


import main.game.GameBoard;
import main.game.command.Command;

/**
 * The DragonCardCommand abstract class represents a command associated with a dragon card in the main.game.
 * This class provides a blueprint for specific types of commands related to dragon cards.
 */
public abstract class DragonCardCommand implements Command {
    protected GameBoard gameBoard;

    public void setGameBoard(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    @Override
    public abstract void execute();
}
