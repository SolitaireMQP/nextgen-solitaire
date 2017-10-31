package domain.moves;

import domain.*;
import java.util.*;

/**
 * A number of cards are dealt from the Stock one at a time
 * to multiple destinations.
 */
public class DeckDealMove extends Move {

    /**
     * Determine conditions for moving column of cards from src to target. 
     */
    public DeckDealMove (String name, Container src, Constraint srcCons, Container target, Constraint tgtCons) {
        super(name, src, srcCons, target, tgtCons);
    }

    /** By definition will allow multiple cards to be moved. Less relevant for deck, but at least consistent. */
    @Override
    public boolean isSingleCardMove() {
        return true;
    }

    /** By definition, deal to all elements in the container. */
    public boolean isSingleDestination() { return false; }

    /**
     * Get element being moved.
     *
     * Even though no card is dragged, this is accurate.
     */
    public Element   getMovableElement() {
        return new Card();
    }
}
