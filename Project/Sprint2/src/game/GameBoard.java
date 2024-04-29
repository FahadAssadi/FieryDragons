package game;

import game.chit.DragonCardManager;
import game.creature.Creature;
import game.creature.CreatureFactory;
import game.player.PlayerManager;
import game.tile.TileManager;

import java.util.List;

public class GameBoard {
    private final DragonCardManager dragonCardManager;
    private final PlayerManager playerManager;
    private final TileManager tileManager;

    public GameBoard() {
        // Create the Creatures list
        List<Creature> creatures = CreatureFactory.createCreatures();

        // Create the Dragon Cards
        this.dragonCardManager = new DragonCardManager();
        this.dragonCardManager.setDragonCards(creatures);

        //Shuffle the Dragon Cards
        this.dragonCardManager.shuffleDragonCards();

        // Create the Players and the Player Queue
        this.playerManager = new PlayerManager();

        // Create the tiles
        this.tileManager = new TileManager();
    }

    public DragonCardManager getDragonCardManager() {
        return dragonCardManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
