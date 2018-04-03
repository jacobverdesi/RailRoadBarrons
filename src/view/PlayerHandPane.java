package view;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import model.Player;

import java.util.Collection;
import java.util.HashSet;

/**
 * Displays the current {@linkplain Player player's} hand as an
 * {@linkplain CardView view} for each type of {@linkplain model.Card card}
 * with the number of cards in the hand indicated. Can flip cards to be face
 * down so that, as each player finishes their turn they do not see the cards
 * in the next player's hand.
 */
public class PlayerHandPane extends GridPane implements RailroadBaronsView {

    /**
     * The {@link CardView CardViews} used to display the card images.
     */
    private final Collection<CardView> cards;

    /**
     * Creates a new {@link PlayerHandPane} with a {@link CardView} for each
     * type of playable card.
     */
    PlayerHandPane() {
        setPadding(new Insets(5));
        cards = new HashSet<>();

        addCardView(CardImage.ORANGE, 0, 0);
        addCardView(CardImage.RED, 1, 0);
        addCardView(CardImage.BLACK, 2, 0);
        addCardView(CardImage.WHITE, 0, 1);
        addCardView(CardImage.BLUE, 1, 1);
        addCardView(CardImage.YELLOW, 2, 1);
        addCardView(CardImage.GREEN, 0, 2);
        addCardView(CardImage.PINK, 1, 2);
        addCardView(CardImage.WILD, 2, 2);
    }

    /**
     * Updates the {@linkplain CardView card view} to display the appropriate
     * number of each type of card based on how many the specified
     * {@linkplain Player player} has in hand.
     *
     * @param player The {@link Player} to use when updating the card counts.
     */
    void updateWithPlayer(Player player) {
        for(CardView cardView : cards) {
            int number = player.countCardsInHand(cardView.getCard());
            cardView.setCardCount(number);
            cardView.setVisible(true);
        }
    }

    /**
     * Flips all {@linkplain CardView card views} so that they are face down.
     * Used when switching from one {@linkplain Player player's} turn to
     * another.
     */
    void placeFaceDown() {
        for(CardView view : cards) {
            view.placeFaceDown();
        }
    }

    /**
     * Flips all of the {@linkplain CardView card views} so that they are face
     * up. Used when a {@linkplain Player player} starts a turn.
     */
    void placeFaceUp() {
        for(CardView view : cards) {
            view.placeFaceUp();
        }
    }

    /**
     * Helper method that adds a {@linkplain CardView card view} for the
     * specified type of card.
     *
     * @param card The {@link CardImage} for which a view should be added.
     * @param row The row in the grid to add the new card view.
     * @param col The column in the gird to add the new card view.
     */
    private void addCardView(CardImage card, int row, int col) {
        CardView view = new CardView(card);
        view.placeFaceDown();
        view.setVisible(false);
        cards.add(view);
        add(view, row, col);
    }
}
