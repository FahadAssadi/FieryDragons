package main.game;

import main.game.player.Player;

public class TicTacToeModel {
    private final String[] board;
    private boolean playerTurn;
    private final Player playerX;
    private final Player playerO;
    private Player winner;

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

    public String getCell(int index) {
        return board[index];
    }

    public Player getCurrentPlayer() {
        return playerTurn ? playerX : playerO;
    }

    public Player getWinner() {
        return winner;
    }

    public void makeMove(int index) {
        if (board[index].isEmpty()) {
            board[index] = playerTurn ? "X" : "O";
            if (checkWin()) {
                winner = playerTurn ? playerX : playerO;
            }
            playerTurn = !playerTurn;
        }
    }

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

    public boolean isBoardFull() {
        for (String cell : board) {
            if (cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            board[i] = "";
        }
        playerTurn = true;
        winner = null;
    }
}
