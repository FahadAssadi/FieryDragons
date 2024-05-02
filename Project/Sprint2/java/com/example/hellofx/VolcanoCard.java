package com.example.hellofx;

import com.example.hellofx.BoardTile;
import com.example.hellofx.Cave;
import com.example.hellofx.GameCharacters;

public class VolcanoCard extends BoardTile {

    // Attributes
    private Boolean indent;
    private com.example.hellofx.Cave cave;

    // Constructors
    public VolcanoCard(Float x, Float y, GameCharacters tileType, Boolean indent) {
        this.x = x;
        this.y = y;
        this.tileType = tileType;
        this.indent = indent;
    }

    // Getters and setters
    public Boolean getIndent() {
        return indent;
    }

    public void setCave(Cave cave) {
        this.cave = cave;
    }
}