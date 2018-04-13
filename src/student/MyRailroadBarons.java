package student;

import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MyRailroadBarons implements RailroadBarons {

    private ArrayList<Player> players=new ArrayList<>();
    private ArrayList<RailroadBaronsObserver> observers=new ArrayList<>();
    private Player currentPlayer;
    private Deck deck;
    private RailroadMap map;



    @Override
    public Player getCurrentPlayer() {
        currentPlayer = players.get(0);
        return players.get(0);
    }

    @Override
    public void addRailroadBaronsObserver(RailroadBaronsObserver observer) {
        System.out.println(observer);
        observers.add(observer);
    }

    @Override
    public void removeRailroadBaronsObserver(RailroadBaronsObserver observer) {
        observers.remove(observer);
    }
    @Override
    public void startAGameWith(RailroadMap map) {
        System.out.println("Test");
        this.map = map;
        ArrayList<Card> cards = new ArrayList<>();

        for(int j=0;j<20;j++){
                cards.add(Card.RED);
                cards.add(Card.GREEN);
                cards.add(Card.ORANGE);
                cards.add(Card.YELLOW);
                cards.add(Card.WILD);
                cards.add(Card.BLACK);
                cards.add(Card.BLUE);
                cards.add(Card.PINK);
                cards.add(Card.WHITE);
        }
        long seed=System.nanoTime();
        Collections.shuffle(cards,new Random(seed));
        this.deck = new MyDeck(cards);
    }

    @Override
    public void startAGameWith(RailroadMap map, Deck deck) {
        players.add(new MyPlayer(Baron.BLUE));
        players.add(new MyPlayer(Baron.RED));
        players.add(new MyPlayer(Baron.YELLOW));
        players.add(new MyPlayer(Baron.GREEN));
        this.map = map;
        this.deck = deck;
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
        if (currentPlayer.canClaimRoute(map.getRoute(row, col))) {
            return true;
        }
        return false;
    }

    @Override
    public void claimRoute(int row, int col) throws RailroadBaronsException {
        map.getRoute(row, col).claim(currentPlayer.getBaron());
    }

    @Override
    public void endTurn() {
        players.remove(currentPlayer);
        players.add(players.size() - 1, currentPlayer);
        for (RailroadBaronsObserver o : observers) {
            o.turnEnded(this, currentPlayer);
        }
        currentPlayer = players.get(0);
        for (RailroadBaronsObserver o : observers) {
            o.turnStarted(this, currentPlayer);
        }
    }

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean gameIsOver() {
        int unable = 0;
        for (Player p : players) {
            if (!p.canContinuePlaying(map.getLengthOfShortestUnclaimedRoute())) {
                unable++;
            }
        }
        int claimedRoutes = 0;
        for (Route r : map.getRoutes()) {
            if (!r.claim(currentPlayer.getBaron())) {
                claimedRoutes++;
            }
        }
        if (unable == players.size() || claimedRoutes == map.getRoutes().size()) {
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
