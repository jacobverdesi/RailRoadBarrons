package student;

import model.Card;
import model.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyDeck implements Deck{

    private ArrayList<Card> deck, drawn;

    public MyDeck(List<Card> deck){
        this.deck = new ArrayList<>();
        this.deck.addAll(deck);
        drawn = new ArrayList<>();
    }

    @Override
    public Card drawACard() {
        Card card = deck.remove(0);
        drawn.add(card);
        return card;
    }

    @Override
    public int numberOfCardsRemaining() {
        return deck.size();
    }

    @Override
    public void reset() {
        deck.addAll(drawn);
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
    }
}
