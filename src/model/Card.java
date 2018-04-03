package model;

/**
 * The types of cards that a player may have in hand.
 */
public enum Card {
    /**
     * This value should be used rather than null to indicate "no card," for
     * example when dealing cards from an empty deck. A card of this type
     * should not be added to a player's hand.
     */
    NONE,

    /**
     * Wild cards may be combined with cards of other colors to claim
     * territory on a Railroad Barons map.
     */
    WILD,
    BACK,
    BLACK,
    BLUE,
    GREEN,
    ORANGE,
    PINK,
    RED,
    WHITE,
    YELLOW;
}
