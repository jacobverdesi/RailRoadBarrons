package student;

import model.*;

import java.util.ArrayList;
import java.util.Collection;

public class MyPlayer implements Player {

    private Baron baron;
    private int score;
    private ArrayList<Route> routes;
    private ArrayList<PlayerObserver> observers = new ArrayList<>();
    private Pair pair;
    private ArrayList<Card> hand, dealt;
    private int pieces;
    private boolean claimedTurn;

    public MyPlayer(Baron baron){
        this.pieces = 45;
        this.baron = baron;
        hand = new ArrayList<>();
        routes = new ArrayList<>();
        claimedTurn = false;
    }

    @Override
    public void reset(Card... dealt) {
        this.pieces = 45;
        hand.clear();
        score = 0;
        routes.clear();
        pair = new MyPair(Card.NONE, Card.NONE);
    }

    @Override
    public void addPlayerObserver(PlayerObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removePlayerObserver(PlayerObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public Baron getBaron() {
        return baron;
    }

    @Override
    public void startTurn(Pair dealt) {
        this.pair = dealt;
        this.hand.add(pair.getFirstCard());
        this.hand.add(pair.getSecondCard());
        for (PlayerObserver p : observers){
            p.playerChanged(this);
        }
    }

    @Override
    public Pair getLastTwoCards() {
        return new MyPair(hand.get(hand.size()-1), hand.get(hand.size()-1));
    }

    @Override
    public int countCardsInHand(Card card) {
        int result = 0;
        for (Card c : hand){
            if (c.equals(card)){
                result++;
            }
        }
        return result;
    }

    @Override
    public int getNumberOfPieces() {
        return pieces;
    }

    @Override
    public boolean canClaimRoute(Route route) {

        if (route.getBaron().equals(Baron.UNCLAIMED) && pieces >= route.getLength() &&
                hand.size() >= route.getLength() && !claimedTurn){
            return true;
        }
        return false;
    }

    @Override
    public void claimRoute(Route route) throws RailroadBaronsException {
        Card first = pair.getFirstCard();
        Card second = pair.getSecondCard();
        ArrayList<Card> firstMatches = new ArrayList<>();
        ArrayList<Card> secondMatches = new ArrayList<>();
        ArrayList<Card> wilds = new ArrayList<>();
        for (Card c : hand) {
            if (c.equals(first)) {
                firstMatches.add(c);
            } else if (c.equals(second)) {
                secondMatches.add(c);
            } else if (c.equals(Card.WILD)) {
                wilds.add(c);
            }
        }

        if (firstMatches.size() < secondMatches.size()) {
            if (firstMatches.size() < route.getLength() && wilds.size() > 0) {
                for (Card h : hand) {
                    if (h.equals(Card.WILD)) {
                        hand.remove(h);
                    }
                }
            }
            for (Card c : firstMatches) {
                for (Card h : hand) {
                    if (c.equals(h)) {
                        hand.remove(h);
                    }
                }
            }
        } else {
            if (secondMatches.size() < route.getLength() && wilds.size() > 0) {
                for (Card h : hand) {
                    if (h.equals(Card.WILD)) {
                        hand.remove(h);
                    }
                }
                for (Card c : secondMatches) {
                    for (Card h : hand) {
                        if (c.equals(h)) {
                            hand.remove(h);
                        }
                    }
                }
            }
            claimedTurn = true;
            route.claim(baron);
            routes.add(route);
            score += route.getPointValue();
            for (PlayerObserver p : observers){
                p.playerChanged(this);
            }
        }
    }

    @Override
    public Collection<Route> getClaimedRoutes() {
        return routes;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean canContinuePlaying(int shortestUnclaimedRoute) {
        if (hand.size() >= shortestUnclaimedRoute && pieces >= shortestUnclaimedRoute){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player: "+baron+" Score: "+score+" Pieces: "+pieces;
    }
}
