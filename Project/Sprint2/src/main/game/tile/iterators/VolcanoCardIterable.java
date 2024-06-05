package main.game.tile.iterators;

import main.game.creature.Creature;
import main.game.creature.iterators.TileableCreatureIterable;
import main.game.tile.TileNode;
import main.game.tile.VolcanoCard;
import main.game.tile.type.VolcanoTileType;
import main.misc.Settings;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class VolcanoCardIterable implements Iterable<VolcanoCard> {
    private VolcanoCard rootVolcanoCard;
    // Path to the directory containing creature images
    private static final String CREATURE_FILE_PATH = "/assets/pngs/creatures/";
    private static final String DEFAULT_VOLCANO_CARD_CONFIG_PATH = "/data/volcanoCard.json";

    public VolcanoCardIterable(TileableCreatureIterable tileableCreatureIterable) {
        constructVolcanoTiles(tileableCreatureIterable);
    }

    private void constructVolcanoTiles(TileableCreatureIterable tileableCreatureIterable) {
        long numVolcanoCards = (long) Settings.getSetting("VolcanoCards");
        long totalNumAnimals = (long) Settings.getSetting("VolcanoCardAnimals");

        Iterator<Creature> tileableCreatureIterator = tileableCreatureIterable.iterator();

        VolcanoCard currVolcanoCard = null;
        int counter = 0;

        while (numVolcanoCards > 0) {
            long numAnimals = totalNumAnimals; // Reset the count for each volcano card

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

            numVolcanoCards--;
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

        for (VolcanoCard volcanoCard : this){
            for (TileNode tileNode : volcanoCard) {
                System.out.println(tileNode.getTempID() + " " + tileNode.getNextTile().getTempID());
            }
        }

    }

    @Override
    public Iterator<VolcanoCard> iterator() {
        return new VolcanoCardIterator();
    }

    private class VolcanoCardIterator implements Iterator<VolcanoCard> {
        private VolcanoCard current;

        public VolcanoCardIterator() {
            this.current = rootVolcanoCard;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public VolcanoCard next() {
            VolcanoCard card = current;
            current = current.getNextVolcanoCard();
            return card;
        }
    }

}
