package com.example.hellofx;

import com.example.hellofx.GameCharacters;

public class DragonCard {
    private com.example.hellofx.GameCharacters tokenType;
    private int cardCount;
    private float x;
    private float y;

    public DragonCard(int cardCount, GameCharacters tokenType) {
        this.tokenType = tokenType;
        this.cardCount = cardCount;
    }

    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }
}