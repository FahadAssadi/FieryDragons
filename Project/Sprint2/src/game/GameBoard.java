package game;

import game.chit.DragonCardManager;
import game.creature.Creature;
import game.creature.CreatureFactory;

import java.util.List;

public class GameBoard {

    public static void main(String[] args) {
        List<Creature> creatures = CreatureFactory.createCreatures();

        DragonCardManager dM = new DragonCardManager(creatures);
        System.out.println();
    }
}
