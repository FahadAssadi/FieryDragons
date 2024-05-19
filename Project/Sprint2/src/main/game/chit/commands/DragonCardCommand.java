package main.game.chit.commands;


import main.game.GameBoard;

/**
 * The DragonCardCommand abstract class represents a command associated with a dragon card in the main.game.
 * This class provides a blueprint for specific types of commands related to dragon cards.
 */
public abstract class DragonCardCommand {
    public abstract void execute(GameBoard gameBoard);
}
