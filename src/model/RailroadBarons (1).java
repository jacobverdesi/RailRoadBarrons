package model;

import java.util.Collection;

/**
 * The interface for a Railroad Barons game. The main entry point into the
 * model for the entire game.
 */
public interface RailroadBarons {
    /**
     * Adds a new {@linkplain RailroadBaronsObserver observer} to the
     * {@linkplain Collection collection} of observers that will be notified
     * when the state of the game changes. Game state changes include:
     * <ul>
     *     <li>A player's turn begins.</li>
     *     <li>A player's turn ends.</li>
     *     <li>The game is over.</li>
     * </ul>
     *
     * @param observer The {@link RailroadBaronsObserver} to add to the
     *                 {@link Collection} of observers.
     */
    void addRailroadBaronsObserver(RailroadBaronsObserver observer);

    /**
     * Removes the {@linkplain RailroadBaronsObserver observer} from the
     * collection of observers that will be notified when the state of the
     * game changes.
     *
     * @param observer The {@link RailroadBaronsObserver} to remove.
     */
    void removeRailroadBaronsObserver(RailroadBaronsObserver observer);

    /**
     * Starts a new {@linkplain RailroadBarons Railroad Barons} game with the
     * specified {@linkplain RailroadMap map} and a default {@linkplain Deck
     * deck of cards}. If a game is currently in progress, the progress is
     * lost. There is no warning!
     *
     * By default, a new game begins with:
     * <ul>
     *     <li>A default deck that contains 20 of each color of card and 20
     *     wild cards.</li>
     *     <li>4 players, each of which has 50 train pieces.</li>
     *     <li>An initial hand of 4 cards dealt from the deck to each
     *     player</li>
     * </ul>
     *
     * @param map The {@link RailroadMap} on which the game will be played.
     */
    void startAGameWith(RailroadMap map);

    /**
     * Starts a new {@linkplain RailroadBarons Railroad Barons} game with the
     * specified {@linkplain RailroadMap map} and {@linkplain Deck deck of
     * cards}. This means that the game should work with any implementation of
     * the {@link Deck} interface (not just a specific implementation)!
     * Otherwise, the starting state of the game is the same as a
     * {@linkplain #startAGameWith(RailroadMap) normal game}.
     *
     * @param map The {@link RailroadMap} on which the game will be played.
     * @param deck The {@link Deck} of cards used to play the game. This may
     *             be ANY implementation of the {@link Deck} interface,
     *             meaning that a valid implementation of the
     *             {@link RailroadBarons} interface should use only the
     *             {@link Deck} interface and not a specific implementation.
     */
    void startAGameWith(RailroadMap map, Deck deck);

    /**
     * Returns the {@linkplain RailroadMap map} currently being used for play.
     * If a game is not in progress, this may be null!
     *
     * @return The {@link RailroadMap} being used for play.
     */
    RailroadMap getRailroadMap();

    /**
     * Returns the number of {@linkplain Card cards} that remain to be dealt
     * in the current game's {@linkplain Deck deck}.
     *
     * @return The number of cards that have not yet been dealt in the game's
     * {@link Deck}.
     */
    int numberOfCardsRemaining();

    /**
     * Returns true iff the current {@linkplain Player player} can claim the
     * {@linkplain Route route} at the specified location, i.e. the player has
     * enough cards and pieces, and the route is not currently claimed by
     * another player. Should delegate to the
     * {@link Player#canClaimRoute(Route)} method on the current player.
     *
     * @param row The row of a {@link Track} in the {@link Route} to check.
     * @param col The column of a {@link Track} in the {@link Route} to check.
     * @return True iff the {@link Route} can be claimed by the current
     * player.
     */
    boolean canCurrentPlayerClaimRoute(int row, int col);

    /**
     * Attempts to claim the {@linkplain Route route} at the specified
     * location on behalf of the current {@linkplain Player player}.
     *
     * @param row The row of a {@link Track} in the {@link Route} to claim.
     * @param col The column of a {@link Track} in the {@link Route} to claim.
     * @throws RailroadBaronsException If the {@link Route} cannot be claimed
     * by the current player.
     */
    void claimRoute(int row, int col) throws RailroadBaronsException;

    /**
     * Called when the current {@linkplain Player player} ends their turn.
     */
    void endTurn();

    /**
     * Returns the {@linkplain Player player} whose turn it is.
     *
     * @return The {@link Player} that is currently taking a turn.
     */
    Player getCurrentPlayer();


    /**
     * Returns all of the {@linkplain Player players} currently playing the
     * game.
     *
     * @return The {@link Player Players} currently playing the game.
     */
    Collection<Player> getPlayers();

    /**
     * Indicates whether or not the game is over. This occurs when no more
     * plays can be made. Reasons include:
     * <ul>
     *     <li>No one player has enough pieces to claim a route.</li>
     *     <li>No one player has enough cards to claim a route.</li>
     *     <li>All routes have been claimed.</li>
     * </ul>
     *
     * @return True if the game is over, false otherwise.
     */
    boolean gameIsOver();
}
