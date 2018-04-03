package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Indicates the owner of a specific space on the map. Either unclaimed (no
 * owner) or one of the players (indicated by color). Each player chooses a
 * baron to represent them during the game.
 */
public enum Baron {
    /**
     * This value should be used rather than null to indicate that something
     * that may be claimed by a baron has not yet been claimed.
     */
    UNCLAIMED,
    RED,
    GREEN,
    YELLOW,
    BLUE;

    /**
     * A {@link Collection} of barons. Does not include the
     * {@link #UNCLAIMED unclaimed} value. Useful for iterating over all of
     * the barons.
     */
    public static final Collection<Baron> BARONS = new ArrayList<>(4);
    static {
        BARONS.add(RED);
        BARONS.add(GREEN);
        BARONS.add(YELLOW);
        BARONS.add(BLUE);
    }
}
