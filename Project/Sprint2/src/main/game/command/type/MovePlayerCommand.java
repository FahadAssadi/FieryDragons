package main.game.command.type;

import main.game.command.Command;
import main.game.tile.TileNode;

public class MovePlayerCommand implements Command {
    private final TileNode tileNode;
    private final int steps;

    public MovePlayerCommand(TileNode tileNode, int steps) {
        this.tileNode = tileNode;
        this.steps = steps;
    }

    @Override
    public void execute() {

    }
}
