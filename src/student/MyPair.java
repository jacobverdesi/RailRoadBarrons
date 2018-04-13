package student;

import model.Card;
import model.Pair;

public class MyPair implements Pair {
    private Card first, second;
    public MyPair(Card first, Card second){
        this.first = first;
        this.second = second;
    }

    public Card getFirstCard() {
        return first;
    }

    public Card getSecondCard() {
        return second;
    }

    @Override
    public String toString() {
        return "First:"+first+" , Second: "+second;
    }
}
