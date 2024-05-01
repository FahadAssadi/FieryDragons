package main.java.com;

public class DragonCard {
    private GameCharacters tokenType;
    private int cardCount;
    private int x;
    private int y;

    public DragonCard(int cardCount, GameCharacters tokenType) {
        this.tokenType = tokenType;
        this.cardCount = cardCount;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}