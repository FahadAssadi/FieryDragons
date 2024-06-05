package main.game;

import main.game.player.Player;

/**
 * This class models the game logic for Tic Tac Toe. It manages the game board, tracks the current player,
 * and determines when the game has been won or if the board is full.
 */
public class TicTacToeModel {
    private final String[] board;
    private boolean playerTurn;
    private final Player playerX;
    private final Player playerO;
    private Player winner;

    /**
     * Constructs a new TicTacToeModel with two specified players.
     * Initializes an empty game board and sets the first player to play as 'X'.
     *
     * @param playerX the player who plays 'X'
     * @param playerO the player who plays 'O'
     */
    public TicTacToeModel(Player playerX, Player playerO) {
        board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = "";
        }
        this.playerX = playerX;
        this.playerO = playerO;
        playerTurn = true;
        winner = null;
    }

    /**
     * Retrieves the content of a specified cell on the game board.
     *
     * @param index the index of the cell on the board
     * @return the content of the cell, either "", "X", or "O"
     */
    public String getCell(int index) {
        return board[index];
    }

    /**
     * Returns the current player based on whose turn it is.
     *
     * @return the current player object
     */
    public Player getCurrentPlayer() {
        return playerTurn ? playerX : playerO;
    }

    /**
     * Retrieves the winner of the game.
     *
     * @return the winning player object if there is a winner, otherwise null
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Makes a move on the game board at the specified index.
     * This method checks for a win after the move and toggles the player turn.
     *
     * @param index the index where the move is to be made
     */
    public void makeMove(int index) {
        if (board[index].isEmpty()) {
            board[index] = playerTurn ? "X" : "O";
            if (checkWin()) {
                winner = playerTurn ? playerX : playerO;
            }
            playerTurn = !playerTurn;
        }
    }

    /**
     * Checks all winning conditions on the game board (rows, columns, diagonals).
     *
     * @return true if there is a win condition met, false otherwise
     */
    public boolean checkWin() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (!board[i].isEmpty() && board[i].equals(board[i + 1]) && board[i].equals(board[i + 2])) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!board[i].isEmpty() && board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                return true;
            }
        }

        // Check diagonals
        if (!board[0].isEmpty() && board[0].equals(board[4]) && board[0].equals(board[8])) {
            return true;
        }
        if (!board[2].isEmpty() && board[2].equals(board[4]) && board[2].equals(board[6])) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the game board is completely filled with no empty cells.
     *
     * @return true if all cells are filled, false otherwise
     */
    public boolean isBoardFull() {
        for (String cell : board) {
            if (cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Resets the game board to an empty state and sets the turn back to the first player.
     */
    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            board[i] = "";
        }
        playerTurn = true;
        winner = null;
    }
}
