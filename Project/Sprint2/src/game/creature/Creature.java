package game.creature;

public class Creature {
    private final int creatureID;
    private final String creatureName;
    private final boolean creatureTileable;
    private final int creatureQuantity;
    private final int creatureRepeat;

    public Creature(int creatureID, String creatureName, boolean creatureTileable, int creatureQuantity, int creatureRepeat) {
        this.creatureID = creatureID;
        this.creatureName = creatureName;
        this.creatureTileable = creatureTileable;
        this.creatureQuantity = creatureQuantity;
        this.creatureRepeat = creatureRepeat;
    }

    public int getCreatureID() {
        return creatureID;
    }

    public String getCreatureName() {
        return creatureName;
    }

    public boolean isTileable(){
        return this.creatureTileable;
    }

    public int getCreatureQuantity() {
        return creatureQuantity;
    }

    public int getCreatureRepeat() {
        return creatureRepeat;
    }
}
