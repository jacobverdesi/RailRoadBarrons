package student;

import model.*;

import java.util.ArrayList;
import java.util.Collection;

public class MyPlayer implements Player {

    private Baron baron;
    private int score;
    private ArrayList<Route> routes;
    private PlayerObserver observer;
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
        this.observer = observer;
    }

    @Override
    public void removePlayerObserver(PlayerObserver observer) {
        this.observer = null;
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
        //TODO : add conditions

        claimedTurn = true;
        route.claim(baron);
        routes.add(route);
        score += route.getPointValue();
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
}
