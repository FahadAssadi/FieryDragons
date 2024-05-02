package com.example.hellofx;

import com.example.hellofx.BoardTile;
import com.example.hellofx.GameCharacters;

public class Cave extends BoardTile {
    // Constructor
    public Cave(Float x, Float y, GameCharacters tileType) {
        this.x = x;
        this.y = y;
        this.tileType = tileType;
    }
}
