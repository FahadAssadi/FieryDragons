package main.game.tile;

import main.game.tile.type.TileType;

public class TileNode {
    private final TileType type;

    // Previous tile in the graph
    private TileNode parent;

    // Follow to reach a volcano tile
    private TileNode left;

    // Follow to potentially reach a cave tile
    private TileNode right;

    public TileNode(TileType type, TileNode parent) {
        this.type = type;
        this.parent = parent;
    }

    public void setParent(TileNode parent) {
        this.parent = parent;
    }

    public void setLeft(TileNode left) {
        this.left = left;
    }

    public void setRight(TileNode right) {
        this.right = right;
    }

    public TileType getType() {
        return type;
    }

    public TileNode getParent() {
        return parent;
    }

    public TileNode getLeft() {
        return left;
    }

    public TileNode getRight() {
        return right;
    }
}
