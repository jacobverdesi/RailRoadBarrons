package student;

import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MyRailroadBarons implements RailroadBarons {

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<RailroadBaronsObserver> observers = new ArrayList<>();
    private Deck deck;
    private RailroadMap map;

    public MyRailroadBarons() {
        players.add(new MyPlayer(Baron.BLUE));
        players.add(new MyPlayer(Baron.RED));
        players.add(new MyPlayer(Baron.YELLOW));
        players.add(new MyPlayer(Baron.GREEN));
    }

    @Override
    public Player getCurrentPlayer() {
        return players.get(0);
    }

    @Override
    public void addRailroadBaronsObserver(RailroadBaronsObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeRailroadBaronsObserver(RailroadBaronsObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void startAGameWith(RailroadMap map) {

        this.map = map;
        ArrayList<Card> cards = new ArrayList<>();
        for (int j = 0; j < 2; j++) {
            for (Card card : Card.values()) {
                if (!card.equals(Card.NONE) && !card.equals(Card.BACK)) {
                    cards.add(card);
                }
            }
        }
        long seed = System.nanoTime();
        Collections.shuffle(cards, new Random(seed));
        this.deck = new MyDeck(cards);
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

    public void dealCards() {
        for (Player player : players) {
            Card first = deck.drawACard();
            Card second = deck.drawACard();
            Pair pair = new MyPair(first, second);
            player.startTurn(pair);
        }
    }

    @Override
    public void startAGameWith(RailroadMap map, Deck deck) {
        this.map = map;
        this.deck = deck;
        dealCards();
        players.add(players.size() - 1, getCurrentPlayer());
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

    @Override
    public RailroadMap getRailroadMap() {
        return map;
    }

    @Override
    public int numberOfCardsRemaining() {
        return deck.numberOfCardsRemaining();
    }

    @Override
    public boolean canCurrentPlayerClaimRoute(int row, int col) {
        return (getCurrentPlayer().canClaimRoute(map.getRoute(row, col)) && map.getRoute(row, col).getBaron().equals(Baron.UNCLAIMED));
    }

    @Override
    public void claimRoute(int row, int col) throws RailroadBaronsException {
        map.getRoute(row, col).claim(getCurrentPlayer().getBaron());
        map.routeClaimed(map.getRoute(row, col));
        getCurrentPlayer().claimRoute(map.getRoute(row, col));
    }

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

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

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
