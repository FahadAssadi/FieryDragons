package main.game.creature;

import java.util.HashMap;
import java.util.Map;

/**
 * The Creature class represents a creature in the main.game.
 */
public class Creature {
    private final int creatureID;
    private final String creatureName;
    private final boolean creatureTileable;
    private final int creatureQuantity;
    private final int creatureRepeat;

    /**
     * Constructs a new Creature object.
     *
     * @param creatureID       The ID of the creature.
     * @param creatureName     The name of the creature.
     * @param creatureTileable Indicates if the creature is tileable.
     * @param creatureQuantity The quantity of the creature.
     * @param creatureRepeat   The number of times the creature repeats.
     */
    public Creature(int creatureID, String creatureName, boolean creatureTileable, int creatureQuantity, int creatureRepeat) {
        this.creatureID = creatureID;
        this.creatureName = creatureName;
        this.creatureTileable = creatureTileable;
        this.creatureQuantity = creatureQuantity;
        this.creatureRepeat = creatureRepeat;
    }

    /**
     * Checks if this creature is equal to another creature based on their IDs.
     *
     * @param other The other creature to compare.
     * @return True if the creatures have the same ID, false otherwise.
     */
    public boolean equals(Creature other) {
        return this.getCreatureID() == other.getCreatureID();
    }

    /**
     * Gets the ID of the creature.
     *
     * @return The ID of the creature.
     */
    public int getCreatureID() {
        return creatureID;
    }

    /**
     * Gets the name of the creature.
     *
     * @return The name of the creature.
     */
    public String getCreatureName() {
        return creatureName;
    }

    /**
     * Checks if the creature is tileable.
     *
     * @return True if the creature is tileable, false otherwise.
     */
    public boolean isTileable(){
        return this.creatureTileable;
    }

    /**
     * Gets the quantity of the creature.
     *
     * @return The quantity of the creature.
     */
    public int getCreatureQuantity() {
        return creatureQuantity;
    }

    /**
     * Gets the number of times the creature repeats.
     *
     * @return The number of times the creature repeats.
     */
    public int getCreatureRepeat() {
        return creatureRepeat;
    }
}
