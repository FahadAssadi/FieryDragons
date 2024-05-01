package game.player;

import game.player.behaviour.BehaviourStrategy;

public class Player {
    private final BehaviourStrategy behaviour;
    private final String colour;
    private int position;
    private int totalMoves;
    private final static int DEFAULT_TOTAL_MOVES = 0;

    public Player(BehaviourStrategy behaviour, int startPosition, String colour) {
        this.behaviour = behaviour;
        this.position = startPosition;
        this.colour = colour;
        this.totalMoves = DEFAULT_TOTAL_MOVES;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour + " " + position;
    }
}
