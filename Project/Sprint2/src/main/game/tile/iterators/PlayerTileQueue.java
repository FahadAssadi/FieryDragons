package main.game.tile.iterators;

import main.game.creature.Creature;
import main.game.creature.iterators.TileableCreatureIterable;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.Player;
import main.game.player.PlayerFactory;
import main.game.snapshot.Memento;
import main.game.tile.TileNode;
import main.game.tile.VolcanoCard;
import main.game.tile.type.CaveTileType;
import main.misc.Settings;

import javax.swing.*;
import java.util.*;

public class PlayerTileQueue implements EventListener, Memento {
    private final List<TileNode> playerTileList = new ArrayList<>();
    private int currIndex;

    // Path to the directory containing creature images
    private static final String CREATURE_FILE_PATH = "/assets/pngs/creatures/";

    public PlayerTileQueue(TileableCreatureIterable tileableCreatureIterable, VolcanoCardIterable volcanoCardIterable) {
        this.constructCaveTiles(tileableCreatureIterable, volcanoCardIterable);
        this.currIndex = 0;

        EventManager.getInstance().subscribe(EventType.PLAYER_TURN_END, this);
    }

    public PlayerTileQueue(Map<String, Object> saveMap, TileableCreatureIterable tileableCreatureIterable, VolcanoCardIterable volcanoCardIterable) {
        this.currIndex = (int) saveMap.get("currIndex");
        this.constructCaveTiles(tileableCreatureIterable, volcanoCardIterable);
        this.loadPlayerTileQueue(saveMap);
        System.out.println();
    }

    public void constructCaveTiles(TileableCreatureIterable tileableCreatureIterable, VolcanoCardIterable volcanoCardIterable){
        int playerDistance = (int) Settings.getSetting("PlayerDistance");

        PlayerFactory playerFactory = new PlayerFactory();
        Iterator<Creature> tileableCreatureIterator = tileableCreatureIterable.iterator();
//        Iterator<VolcanoCard> volcanoCardIterator = volcanoCardIterable.iterator();
        Iterator<VolcanoCard> volcanoCardIterator = volcanoCardIterable.iterator();

        VolcanoCard currVolcanoCard = volcanoCardIterator.next();
        Iterator<TileNode> volcanoTileIterator = currVolcanoCard.iterator();

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
                if (!volcanoTileIterator.hasNext()) {
                    if (!volcanoCardIterator.hasNext()) {
                        volcanoCardIterator = volcanoCardIterable.iterator();  // Cycle through VolcanoCards if needed.
                        if (!volcanoCardIterator.hasNext()) return;  // Handle empty iterator on reset.
                    }
                    currVolcanoCard = volcanoCardIterator.next();
                    volcanoTileIterator = currVolcanoCard.iterator();
                    if (!volcanoTileIterator.hasNext()) return;  // Handle empty TileNodes in the new VolcanoCard.
                }
                TileNode volcanoTile = volcanoTileIterator.next();
                currVolcanoTile = volcanoTile;
            }

        }
    }

    public void loadPlayerTileQueue(Map<String, Object> saveMap) {
        for (int i = 0; i < this.playerTileList.size(); i++) {
            Map<String, Object> playerTileNodeMap = (Map<String, Object>) saveMap.get("Player" + i);
            int playerSteps = (int) playerTileNodeMap.get("totalMoves");

            TileNode currPlayerTileNode = this.playerTileList.get(i);
            TileNode nextTileNode = null;

            // Move the player to that tile
            try {
                nextTileNode = currPlayerTileNode.traverseForward(playerSteps, playerSteps);
                currPlayerTileNode.movePlayerToTile(nextTileNode, playerSteps);
            } catch (Exception _) {
                // this will never cause an exception
            }

            updateCurrPlayerTileNode(nextTileNode);
            queueNextTurn();
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

    public void updateCurrPlayerTileNode(TileNode tileNode) {
        this.playerTileList.set(this.currIndex, tileNode);
    }

    @Override
    public String toString() {
        return this.playerTileList.toString();
    }

    @Override
    public Map<String, Object> save(Map<String, Object> map) {
        map.put("currIndex", this.currIndex);
        int count = 0;
        for (TileNode tileNode: this.playerTileList) {
            map.put("Player"+ count++, tileNode.getType().getPlayer().save(new LinkedHashMap<>()));
        }

        return map;
    }

}
