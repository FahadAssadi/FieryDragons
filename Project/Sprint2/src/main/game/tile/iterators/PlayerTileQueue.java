package main.game.tile.iterators;

import main.game.creature.Creature;
import main.game.creature.iterators.TileableCreatureIterable;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.Player;
import main.game.player.PlayerFactory;
import main.game.tile.TileNode;
import main.game.tile.type.CaveTileType;
import main.misc.Settings;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerTileQueue implements EventListener {
    private final List<TileNode> playerTileList = new ArrayList<>();
    private int currIndex;

    // Path to the directory containing creature images
    private static final String CREATURE_FILE_PATH = "/assets/pngs/creatures/";

    public PlayerTileQueue(TileableCreatureIterable tileableCreatureIterable, VolcanoTileIterable volcanoTileIterable) {
        this.constructCaveTiles(tileableCreatureIterable, volcanoTileIterable);
        this.currIndex = 0;

        EventManager.getInstance().subscribe(EventType.PLAYER_TURN_END, this);
    }

    public void constructCaveTiles(TileableCreatureIterable tileableCreatureIterable, VolcanoTileIterable volcanoTileIterable){
        int playerDistance = (int) Settings.getSetting("PlayerDistance");

        PlayerFactory playerFactory = new PlayerFactory();
        Iterator<Creature> tileableCreatureIterator = tileableCreatureIterable.iterator();
        Iterator<TileNode> volcanoTileIterator = volcanoTileIterable.iterator();

        TileNode currVolcanoTile = volcanoTileIterator.next();

        int count = 0;

        while (playerFactory.hasNext()){
            if (!tileableCreatureIterator.hasNext()) {
                tileableCreatureIterator = tileableCreatureIterable.iterator(); // Reset the iterator
            }

            Creature currCreature = tileableCreatureIterator.next();
            Player player = playerFactory.next();

            String imagePath = CREATURE_FILE_PATH + currCreature.getCreatureName() + "_1.png";
            ImageIcon caveTileImage = new ImageIcon(getClass().getResource(imagePath));

            TileNode caveTileNode = new TileNode(new CaveTileType(caveTileImage, currCreature, player), null, count++);
            caveTileNode.setNextTile(currVolcanoTile);

            currVolcanoTile.setAdjacentTile(caveTileNode);

            this.playerTileList.add(caveTileNode);

            for (int i = 0; i < playerDistance; i++){
                currVolcanoTile = volcanoTileIterator.next();
            }
        }
    }

    public void queueNextTurn(){
        this.currIndex = (++this.currIndex) % this.playerTileList.size();
    }

    public TileNode getCurrPlayerTileNode(){
        return this.playerTileList.get(currIndex);
    }

    @Override
    public void update(EventType eventType) {
        if (eventType == EventType.PLAYER_TURN_END) {
            this.queueNextTurn();
        }
    }

}
