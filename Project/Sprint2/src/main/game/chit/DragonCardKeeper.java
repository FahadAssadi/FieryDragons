package main.game.chit;

import main.game.creature.CreatureKeeper;
import main.game.creature.iterators.CreatureIterable;

public class DragonCardKeeper {
    private DragonCard selectedDragonCard;
    private DragonCardIterable dragonCardIterable;

    public DragonCardKeeper(CreatureKeeper creatureKeeper) {
        CreatureIterable creatureIterable = creatureKeeper.getCreatureIterable();
        dragonCardIterable = new DragonCardIterable(creatureIterable);
    }

    public DragonCardIterable getDragonCardIterable() {
        return dragonCardIterable;
    }

    public DragonCard getSelectedDragonCard() {
        return selectedDragonCard;
    }

    public void setSelectedDragonCard(DragonCard selectedDragonCard) {
        this.selectedDragonCard = selectedDragonCard;
    }
}