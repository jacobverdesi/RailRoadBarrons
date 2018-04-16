package student;

import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
/**
 * The interface for a Railroad Barons game. The main entry point into the
 * model for the entire game.
 * @Author Jacob Verdesi
 * @Author Andrew Yankowsky
 */
public class MyRailroadBarons implements RailroadBarons {

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<RailroadBaronsObserver> observers = new ArrayList<>();
    private Deck deck;
    private RailroadMap map;

    /**
     * initilize the players
     */
    public MyRailroadBarons() {
        players.add(new MyPlayer(Baron.BLUE));
        players.add(new MyPlayer(Baron.RED));
        players.add(new MyPlayer(Baron.YELLOW));
        players.add(new MyPlayer(Baron.GREEN));
    }
    /**
     * Returns the {@linkplain Player player} whose turn it is.
     *
     * @return The {@link Player} that is currently taking a turn.
     */
    @Override
    public Player getCurrentPlayer() {
        return players.get(0);
    }

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
    @Override
    public void addRailroadBaronsObserver(RailroadBaronsObserver observer) {
        observers.add(observer);
    }
    /**
     * Removes the {@linkplain RailroadBaronsObserver observer} from the
     * collection of observers that will be notified when the state of the
     * game changes.
     *
     * @param observer The {@link RailroadBaronsObserver} to remove.
     */
    @Override
    public void removeRailroadBaronsObserver(RailroadBaronsObserver observer) {
        observers.remove(observer);
    }
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
    @Override
    public void startAGameWith(RailroadMap map) {
        for (Player player:players){
            player.reset();
        }
        this.map = map;

        this.deck = new MyDeck();
        dealCards();
        Card first = deck.drawACard();
        Card second = deck.drawACard();
        Pair pair = new MyPair(first, second);
        players.add(players.size(), getCurrentPlayer());
        players.remove(getCurrentPlayer());
        getCurrentPlayer().startTurn(pair);
        for (RailroadBaronsObserver observer : observers) {
            observer.turnStarted(this, getCurrentPlayer());
        }
    }

    /**
     * deals 2 cards to players
     */
    private void dealCards() {
        for (Player player : players) {
            Card first = deck.drawACard();
            Card second = deck.drawACard();
            Pair pair = new MyPair(first, second);
            player.startTurn(pair);
        }
    }
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
    @Override
    public void startAGameWith(RailroadMap map, Deck deck) {
        for (Player player:players){
            player.reset();
        }
        this.map = map;
        this.deck = deck;
        dealCards();
        players.add(players.size() - 1, getCurrentPlayer());
        Card first = deck.drawACard();
        Card second = deck.drawACard();
        Pair pair = new MyPair(first, second);
        getCurrentPlayer().startTurn(pair);
        for (RailroadBaronsObserver observer : observers) {
            observer.turnStarted(this, getCurrentPlayer());
        }
    }
    /**
     * Returns the {@linkplain RailroadMap map} currently being used for play.
     * If a game is not in progress, this may be null!
     *
     * @return The {@link RailroadMap} being used for play.
     */
    @Override
    public RailroadMap getRailroadMap() {
        return map;
    }
    /**
     * Returns the number of {@linkplain Card cards} that remain to be dealt
     * in the current game's {@linkplain Deck deck}.
     *
     * @return The number of cards that have not yet been dealt in the game's
     * {@link Deck}.
     */
    @Override
    public int numberOfCardsRemaining() {
        return deck.numberOfCardsRemaining();
    }

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
    @Override
    public boolean canCurrentPlayerClaimRoute(int row, int col) {
        return (getCurrentPlayer().canClaimRoute(map.getRoute(row, col)) && map.getRoute(row, col).getBaron().equals(Baron.UNCLAIMED));
    }
    /**
     * Attempts to claim the {@linkplain Route route} at the specified
     * location on behalf of the current {@linkplain Player player}.
     *
     * @param row The row of a {@link Track} in the {@link Route} to claim.
     * @param col The column of a {@link Track} in the {@link Route} to claim.
     * @throws RailroadBaronsException If the {@link Route} cannot be claimed
     * by the current player.
     */
    @Override
    public void claimRoute(int row, int col) throws RailroadBaronsException {
        if(getCurrentPlayer().canClaimRoute(map.getRoute(row,col))) {
            map.getRoute(row, col).claim(getCurrentPlayer().getBaron());
            map.routeClaimed(map.getRoute(row, col));
            getCurrentPlayer().claimRoute(map.getRoute(row, col));
        }
        else{
            throw new RailroadBaronsException("Cannot claim route");
        }
    }
    /**
     * Called when the current {@linkplain Player player} ends their turn.
     */
    @Override
    public void endTurn() {
        for (RailroadBaronsObserver o : observers) {
            o.turnEnded(this, getCurrentPlayer());
        }
        players.add(players.size(), getCurrentPlayer());
        players.remove(getCurrentPlayer());
        if (!gameIsOver()) {
            if (deck.numberOfCardsRemaining() >= 2) {
                Card first = deck.drawACard();
                Card second = deck.drawACard();
                Pair pair = new MyPair(first, second);
                getCurrentPlayer().startTurn(pair);
            } else {
                getCurrentPlayer().startTurn(new MyPair(Card.NONE, Card.NONE));
            }
            for (RailroadBaronsObserver o : observers) {
                o.turnStarted(this, getCurrentPlayer());
            }
        }
    }
    /**
     * Returns all of the {@linkplain Player players} currently playing the
     * game.
     *
     * @return The {@link Player Players} currently playing the game.
     */
    @Override
    public Collection<Player> getPlayers() {
        return players;
    }
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
    @Override
    public boolean gameIsOver() {
        boolean unable = false;
        boolean noPieces = false;
        for (Player p : players) {
            if (!p.canContinuePlaying(map.getLengthOfShortestUnclaimedRoute()) && deck.numberOfCardsRemaining() == 0) {
                unable = true;
            }
            if (p.getNumberOfPieces() == 0) {
                noPieces = true;
            }
        }
        int claimedRoutes = 0;
        for (Route r : map.getRoutes()) {
            if (r.getBaron().equals(Baron.UNCLAIMED)) {
                claimedRoutes++;
            }
        }
        if (unable || noPieces || claimedRoutes == 0) {
            int highScore = 0;
            Player best = null;
            for (Player p : players) {
                if (p.getScore() > highScore) {
                    highScore = p.getScore();
                    best = p;
                }
            }
            for (RailroadBaronsObserver o : observers) {
                o.gameOver(this, best);
            }
            return true;
        }
        return false;
    }
}
