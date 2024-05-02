package main.java.com;

public class DragonCard {
    private GameCharacters tokenType;
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