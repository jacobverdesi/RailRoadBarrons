package student;

import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class MyPlayer implements Player {

    private Baron baron;
    private int score;
    private ArrayList<Route> routes;
    private ArrayList<PlayerObserver> observers = new ArrayList<>();
    private Pair pair;
    private ArrayList<Card> hand;
    private int pieces;
    private boolean claimedTurn;

    public MyPlayer(Baron baron){
        this.pieces = 45;
        this.baron = baron;
        hand = new ArrayList<>();

        for (PlayerObserver p : observers){
            p.playerChanged(this);
        }
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
        return new MyPair(pair.getFirstCard(), pair.getSecondCard());
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
        System.out.println("CanClaimRoute");
       return pieces >= route.getLength() && hasEnoughCards(route.getLength())&& !claimedTurn;

    }
    private boolean hasEnoughCards(int size){
        if(getNumColorCards(Card.WILD)>0) {
            size--;
        }
        for (Card card:Card.values()){
            if(!card.equals(Card.NONE)&&!card.equals(Card.BACK)&&!card.equals(Card.WILD)){
               if (getNumColorCards(card)>=size){
                   return true;
               }
            }
        }
        return false;

    }
    private int getNumColorCards(Card card){
        int total=0;
        for(Card card1:hand){
            if(card1.equals(card)){
                total++;
            }
        }
        return total;
    }
    private ArrayList<Card> getEnoughCards(int size){
        ArrayList<Card> cards=new ArrayList<>();
        //see if enough without wild
        for(Card card:Card.values()){
            if(!card.equals(Card.NONE)&&!card.equals(Card.BACK)&&!card.equals(Card.WILD)){
                if (getNumColorCards(card)==size){
                    for(int i=0;i<size;i++){
                        cards.add(card);
                    }
                    return cards;
                }
            }
        }
        for(Card card:Card.values()){
            if(!card.equals(Card.NONE)&&!card.equals(Card.BACK)&&!card.equals(Card.WILD)){
                if (getNumColorCards(card)>size){
                    for(int i=0;i<size;i++){
                        cards.add(card);
                    }
                    return cards;
                }
            }
        }
        for(Card card:Card.values()){
            if(!card.equals(Card.NONE)&&!card.equals(Card.BACK)&&!card.equals(Card.WILD)){
                if (getNumColorCards(card)==size-1&&getNumColorCards(Card.WILD)>0){
                    for(int i=0;i<size-1;i++){
                        cards.add(card);
                    }
                    cards.add(Card.WILD);
                    return cards;
                }
            }
        }
    return cards;
    }

    @Override
    public void claimRoute(Route route) throws RailroadBaronsException {
        System.out.println("ClaimRoute");
        if(canClaimRoute(route)){
//        Card first = pair.getFirstCard();
//        Card second = pair.getSecondCard();
//            ArrayList<Card> firstMatches = new ArrayList<>();
//            ArrayList<Card> secondMatches = new ArrayList<>();
//            ArrayList<Card> wilds = new ArrayList<>();
//        for (Card c : hand) {
//            if (c.equals(first)) {
//                firstMatches.add(c);
//            } else if (c.equals(second)) {
//                secondMatches.add(c);
//            } else if (c.equals(Card.WILD)) {
//                wilds.add(c);
//            }
//        }
//        if (firstMatches.size() < secondMatches.size()) {
//            if (firstMatches.size() < route.getLength() && wilds.size() > 0) {
//                for (Card h : hand) {
//                    if (h.equals(Card.WILD)) {
//                        hand.remove(h);
//                    }
//                }
//            }
//            for (Card c : firstMatches) {
//                for (Card h : hand) {
//                    if (c.equals(h)) {
//                        hand.remove(h);
//                    }
//                }
//            }
//        } else {
//            if (secondMatches.size() < route.getLength() && wilds.size() > 0) {
//                for (Card h : hand) {
//                    if (h.equals(Card.WILD)) {
//                        hand.remove(h);
//                    }
//                }
//                for (Card c : secondMatches) {
//                    for (Card h : hand) {
//                        if (c.equals(h)) {
//                            hand.remove(h);
//                        }
//                    }
//                }
//            }
//        }
            System.out.println(hand);
            ArrayList<Card> cardArrayList=new ArrayList<>();
            cardArrayList=getEnoughCards(route.getLength());
            for(Card card:cardArrayList){
                hand.remove(card);
            }
            System.out.println(hand);
            for (PlayerObserver p : observers) {
                p.playerChanged(this);
            }
           // claimedTurn = true;
            route.claim(baron);
            routes.add(route);
            score += route.getPointValue();
        }
        else {
            throw new RailroadBaronsException("Cannot claim route");
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
        return baron+" Baron";
    }
}
