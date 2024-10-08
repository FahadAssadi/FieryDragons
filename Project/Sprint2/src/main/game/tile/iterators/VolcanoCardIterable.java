package main.game.tile.iterators;

import main.game.creature.Creature;
import main.game.creature.iterators.TileableCreatureIterable;
import main.game.snapshot.Memento;
import main.game.tile.TileNode;
import main.game.tile.VolcanoCard;
import main.game.tile.type.VolcanoTileType;
import main.misc.Utility;
import org.json.simple.JSONArray;

import javax.swing.*;
import java.util.*;

/**
 * Represents an iterable collection of VolcanoCards.
 * This class also handles the creation and linking of these cards based on provided creatures.
 */
public class VolcanoCardIterable implements Iterable<VolcanoCard>, Memento {
    private VolcanoCard rootVolcanoCard;
    // Path to the directory containing creature images
    private static final String CREATURE_FILE_PATH = "/assets/pngs/creatures/";
    private static final String DEFAULT_VOLCANO_CARD_CONFIG_PATH = "/data/volcanoCard.json";

    /**
     * Constructs a VolcanoCardIterable using a provided iterable of creatures.
     * It initializes the construction of volcano tiles which form the volcano cards.
     *
     * @param tileableCreatureIterable an iterable of creatures used to populate the volcano cards
     */
    public VolcanoCardIterable(TileableCreatureIterable tileableCreatureIterable) {
        constructVolcanoTiles(tileableCreatureIterable);
    }

    public VolcanoCardIterable(Map<String , Object> saveMap, TileableCreatureIterable tileableCreatureIterable) {
        loadVolcanoTiles(tileableCreatureIterable, saveMap);
    }

    /**
     * Constructs volcano tiles and organizes them into a linked list of VolcanoCards.
     * Each card is populated using the creatures from the provided iterable.
     * Images are assigned based on creature names and predefined paths.
     *
     * @param tileableCreatureIterable an iterable of creatures used to populate the volcano tiles
     */
    private void constructVolcanoTiles(TileableCreatureIterable tileableCreatureIterable) {
        JSONArray volcanoCardsArrayFromJson = Utility.readJSONFileToArr(getClass().getResourceAsStream(DEFAULT_VOLCANO_CARD_CONFIG_PATH));

        int totalVolcanoCards = volcanoCardsArrayFromJson.size();

        Iterator<Creature> tileableCreatureIterator = tileableCreatureIterable.iterator();

        VolcanoCard currVolcanoCard = null;

        int counter = 0; // counter just for temporary id of nodes
        int cardCounter = 0; // counting volcano cards

        while (totalVolcanoCards > 0) {
            long numAnimals = (long) Utility.getObjFromArr(volcanoCardsArrayFromJson, cardCounter).get("animals");

            TileNode prevNode = null;
            TileNode startNode = null;

            while (numAnimals > 0) {
                if (!tileableCreatureIterator.hasNext()) {
                    tileableCreatureIterator = tileableCreatureIterable.iterator(); // Reset the iterator if exhausted
                }

                Creature currCreature = tileableCreatureIterator.next();
                String imagePath = CREATURE_FILE_PATH + currCreature.getCreatureName() + "_1.png";
                ImageIcon volcanoTileImage = new ImageIcon(getClass().getResource(imagePath));

                TileNode currNode = new TileNode(new VolcanoTileType(volcanoTileImage, currCreature), prevNode, counter++);

                if (prevNode != null) {
                    prevNode.setNextTile(currNode);
                    currNode.setPreviousTile(prevNode);
                } else {
                    startNode = currNode; // Start node is the first node created
                }

                prevNode = currNode;
                numAnimals--;
            }

            if (currVolcanoCard != null) {
                VolcanoCard newVolcanoCard = new VolcanoCard(startNode);
                currVolcanoCard.setNextVolcanoCard(newVolcanoCard);
                currVolcanoCard = newVolcanoCard;
            } else {
                currVolcanoCard = new VolcanoCard(startNode);
                rootVolcanoCard = currVolcanoCard;
            }
            cardCounter ++;
            totalVolcanoCards--;
        }


        ArrayList<TileNode> volcanoTiles = new ArrayList<>();
        for (VolcanoCard volcanoCard : this){
           for (TileNode tileNode : volcanoCard) {
               volcanoTiles.add(tileNode);
           }
        }

         // connecting nodes
         for (int i = 0; i < volcanoTiles.size() -1 ; i++) {
              TileNode currNode = volcanoTiles.get(i);

              if (currNode.getNextTile() == null) {
                   TileNode nextNode = volcanoTiles.get(i + 1);

                   currNode.setNextTile(nextNode);
                   nextNode.setPreviousTile(currNode);
              }
         }

         // Connecting final and root node
         volcanoTiles.getLast().setNextTile(rootVolcanoCard.getStartTileNode());
         rootVolcanoCard.getStartTileNode().setPreviousTile(volcanoTiles.getLast());
    }

    private void loadVolcanoTiles (TileableCreatureIterable tileableCreatureIterable, Map<String , Object> saveMap) {
        VolcanoCard currVolcanoCard = null;
        int counter = 0; // counter just for temporary id of nodes

        for (int i = 0; i < saveMap.size() ; i++) {
            Map<String , Object> volcanoCard = (Map<String, Object>) saveMap.get("volcanoCard" + i);

            TileNode prevNode = null;
            TileNode startNode = null;

            for (int j = 0; j < volcanoCard.size(); j++) {
                Map<String , Object> tileNode = (Map<String, Object>) volcanoCard.get("tileNode" + j);
                int creatureId = (int) tileNode.get("Creature");

                Creature currCreature = null;
                for (Creature creature : tileableCreatureIterable) {
                    if (creature.getCreatureID() == creatureId) {
                        currCreature = creature;
                    }
                }

                String imagePath = CREATURE_FILE_PATH + currCreature.getCreatureName() + "_1.png";
                ImageIcon volcanoTileImage = new ImageIcon(getClass().getResource(imagePath));

                TileNode currNode = new TileNode(new VolcanoTileType(volcanoTileImage, currCreature), prevNode, counter++);

                if (prevNode != null) {
                    prevNode.setNextTile(currNode);
                    currNode.setPreviousTile(prevNode);
                } else {
                    startNode = currNode; // Start node is the first node created
                }

                prevNode = currNode;
            }

            if (currVolcanoCard != null) {
                VolcanoCard newVolcanoCard = new VolcanoCard(startNode);
                currVolcanoCard.setNextVolcanoCard(newVolcanoCard);
                currVolcanoCard = newVolcanoCard;
            } else {
                currVolcanoCard = new VolcanoCard(startNode);
                rootVolcanoCard = currVolcanoCard;
            }
        }


        ArrayList<TileNode> volcanoTiles = new ArrayList<>();
        for (VolcanoCard volcanoCard : this){
            for (TileNode tileNode : volcanoCard) {
                volcanoTiles.add(tileNode);
            }
        }

        // connecting nodes
        for (int i = 0; i < volcanoTiles.size() -1 ; i++) {
            TileNode currNode = volcanoTiles.get(i);

            if (currNode.getNextTile() == null) {
                TileNode nextNode = volcanoTiles.get(i + 1);

                currNode.setNextTile(nextNode);
                nextNode.setPreviousTile(currNode);
            }
        }

        // Connecting final and root node
        volcanoTiles.getLast().setNextTile(rootVolcanoCard.getStartTileNode());
        rootVolcanoCard.getStartTileNode().setPreviousTile(volcanoTiles.getLast());
    }
    /**
     * Provides an iterator over the collection of VolcanoCards.
     *
     * @return an iterator that allows traversing the linked list of volcano cards
     */
    @Override
    public Iterator<VolcanoCard> iterator() {
        return new VolcanoCardIterator();
    }

    /**
     * Saves the VolcanoCard objects in the iterable to a map.
     *
     * @param  map  the map to save the VolcanoCard objects to
     * @return      the map containing the saved VolcanoCard objects
     */
    @Override
    public Map<String, Object> save(Map<String, Object> map) {
        int count = 0;
        for (VolcanoCard volcanoCard : this) {
            map.put("volcanoCard" + count++, volcanoCard.save(new LinkedHashMap<>()));
        }

        return map;
    }

    /**
     * Iterator for traversing through VolcanoCards in the iterable.
     */
    private class VolcanoCardIterator implements Iterator<VolcanoCard> {
        private VolcanoCard current;

        /**
         * Constructs a new VolcanoCardIterator starting with the root volcano card.
         */
        public VolcanoCardIterator() {
            this.current = rootVolcanoCard;
        }

        /**
         * Checks if there is another volcano card to return.
         *
         * @return true if there is another card, false otherwise
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next VolcanoCard in the sequence.
         *
         * @return the next VolcanoCard
         */
        @Override
        public VolcanoCard next() {
            VolcanoCard card = current;
            current = current.getNextVolcanoCard();
            return card;
        }
    }

}
