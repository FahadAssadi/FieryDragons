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
        this.constructCaveTiles(tileableCreatureIterable, volcanoCardIterable);
        this.loadPlayerTileQueue(saveMap);

        this.currIndex = (int) saveMap.get("currIndex");
    }

    /**
     * Constructs cave tiles for the player based on the given tileable creature iterator and volcano card iterator.
     *
     * @param  tileableCreatureIterable  an iterator over tileable creatures
     * @param  volcanoCardIterable      an iterator over volcano cards
     */
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

    /**
     * Loads the player tile queue from the given saved map.
     *
     * @param  saveMap  a map containing the saved player tile information
     */
    public void loadPlayerTileQueue(Map<String, Object> saveMap) {
        for (int i = 0; i < this.playerTileList.size(); i++) {
            Map<String, Object> playerTileNodeMap = (Map<String, Object>) saveMap.get("Player" + i);
            int playerSteps = (int) playerTileNodeMap.get("totalMoves");

            if (playerSteps != 0) {
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
            }

            queueNextTurn();
        }
    }

    /**
     * Increments the current index and wraps around to the beginning of the player tile list if the current index exceeds the size of the list.
     *
     * @return void
     */
    public void queueNextTurn(){
        this.currIndex = (++this.currIndex) % this.playerTileList.size();
    }

    /**
     * Retrieves the current player tile node from the player tile list.
     *
     * @return  the current player tile node
     */
    public TileNode getCurrPlayerTileNode(){
        return this.playerTileList.get(currIndex);
    }

    /**
     * Updates the state of the object based on the given event type. If the event type is PLAYER_TURN_END,
     * it calls the queueNextTurn() method to increment the current index and wrap around to the beginning of the player tile list.
     *
     * @param  eventType  the type of event that occurred
     */
    @Override
    public void update(EventType eventType) {
        if (eventType == EventType.PLAYER_TURN_END) {
            this.queueNextTurn();
        }
    }

    /**
     * Updates the current player tile node in the player tile list.
     *
     * @param  tileNode  the new tile node to be set at the current index
     */
    public void updateCurrPlayerTileNode(TileNode tileNode) {
        this.playerTileList.set(this.currIndex, tileNode);
    }

    /**
     * Returns the number of elements in the playerTileList.
     *
     * @return the number of elements in the playerTileList
     */
    public int size() {
        return this.playerTileList.size();
    }

    /**
     * Returns a string representation of the playerTileList.
     *
     * @return         a string representation of the playerTileList
     */
    @Override
    public String toString() {
        return this.playerTileList.toString();
    }

    /**
     * Saves the current state of the object into a map.
     *
     * @param  map  the map to store the saved state
     * @return      the map containing the saved state
     */
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
