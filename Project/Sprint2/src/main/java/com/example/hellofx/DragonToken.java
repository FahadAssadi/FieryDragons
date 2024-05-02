package com.example.hellofx;

import com.example.hellofx.BoardTile;
import com.example.hellofx.Cave;

public class DragonToken {
    private String color;
    private com.example.hellofx.Cave homeCave;
    private float x;
    private float y;

    public DragonToken(String color, com.example.hellofx.Cave homeCave) {
        this.color = color;
    }


    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setHomeCave(Cave homeCave) {
        this.homeCave = homeCave;
    }

    // Methods
    public void updateScore() {
        // Method implementation
    }

    public void move(BoardTile tile) {
        // Method implementation
        this.setPos(tile.x, tile.y);
    }
}
