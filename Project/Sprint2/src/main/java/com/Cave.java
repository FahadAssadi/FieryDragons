package main.java.com;

public class Cave extends BoardTile {
    // Constructor
    public Cave(Float x, Float y, Float orientation, GameCharacters tileType, boolean isExplored) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.tileType = tileType;
    }
}
