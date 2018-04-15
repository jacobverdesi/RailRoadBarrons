package student;

import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MyRailroadBarons implements RailroadBarons {

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<RailroadBaronsObserver> observers=new ArrayList<>();
    private Deck deck;
    private RailroadMap map;
    public MyRailroadBarons(){
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
        //players.add(players.size() - 1, getCurrentPlayer());
        System.out.println(deck.numberOfCardsRemaining());
        Card first = deck.drawACard();
        Card second= deck.drawACard();
        Pair pair=new MyPair(first,second);
        getCurrentPlayer().startTurn(pair);
            for (RailroadBaronsObserver observer:observers){
                observer.turnStarted(this,getCurrentPlayer());
               // observer.notify();
            }

    }

    @Override
    public void startAGameWith(RailroadMap map, Deck deck) {

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
        if (getCurrentPlayer().canClaimRoute(map.getRoute(row, col))) {
            return true;
        }
        return false;
    }

    @Override
    public void claimRoute(int row, int col) throws RailroadBaronsException {
        map.getRoute(row, col).claim(getCurrentPlayer().getBaron());
    }

    @Override
    public void endTurn() {
        for (RailroadBaronsObserver o : observers) {
            o.turnEnded(this, getCurrentPlayer());
        }

        players.remove(getCurrentPlayer());
        players.add(players.size() , getCurrentPlayer());
        Card first = deck.drawACard();
        Card second= deck.drawACard();
        Pair pair=new MyPair(first,second);
        getCurrentPlayer().startTurn(pair);
        for (RailroadBaronsObserver o : observers) {
            o.turnStarted(this, getCurrentPlayer());
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
            if (!r.claim(getCurrentPlayer().getBaron())) {
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
