package domain.moves;

import domain.*;
import domain.constraints.Truth;

/**
 * Deck is reconstituted from the target elements.
 */
public class ResetDeckMove extends ActualMove {

    /**
     * Determine conditions for resetting deck. 
     */
    public ResetDeckMove (String name, Container src, Constraint srcCons, Container target) {
        super(name, src, srcCons, target, new Truth());
    }

    /** By definition will allow multiple cards to be moved. Less relevant for deck but at least consistent. */
    @Override
    public boolean isSingleCardMove() {
        return false;
    }

    /** By definition, remove from all elements within the container. */
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
