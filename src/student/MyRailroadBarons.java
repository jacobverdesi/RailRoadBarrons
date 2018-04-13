package student;

import model.*;

import java.util.ArrayList;
import java.util.Collection;

public class MyRailroadBarons implements RailroadBarons {

    private ArrayList<Player> players;
    private ArrayList<RailroadBaronsObserver> observers;
    private Player currentPlayer;
    private Deck deck;
    private RailroadMap map;

    public MyRailroadBarons(ArrayList<Player> players){
        this.players = players;
    }

    @Override
    public Player getCurrentPlayer() {
        currentPlayer = players.get(0);
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
        //TODO : make and add random cards to list
        this.deck = new MyDeck(cards);
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
        if (currentPlayer.canClaimRoute(map.getRoute(row, col))){
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
        players.add(players.size()-1, currentPlayer);
        for (RailroadBaronsObserver o : observers){
            o.turnEnded(this, currentPlayer);
        }
        currentPlayer = players.get(0);
        for (RailroadBaronsObserver o : observers){
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
        for (Player p : players){
            if (!p.canContinuePlaying(map.getLengthOfShortestUnclaimedRoute())){
                unable++;
            }
        }
        int claimedRoutes = 0;
        for (Route r : map.getRoutes()){
            if (!r.claim(currentPlayer.getBaron())){
                claimedRoutes++;
            }
        }
        if (unable == players.size() || claimedRoutes == map.getRoutes().size()){
            int highScore = 0;
            Player best = null;
            for (Player p : players){
                if (p.getScore() > highScore){
                    highScore = p.getScore();
                    best = p;
                }
            }
            for (RailroadBaronsObserver o : observers){
                o.gameOver(this, best);
            }
            return true;
        }

        return false;
    }
}
