package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.Card;

/**
 * Displays a {@link Card card} in the user interface, for example the cards
 * in the current {@linkplain model.Player player's} hand and the cards
 * dealt to player at the start of each turn. In some cases a number is
 * displayed as well as the card image (e.g. to show how many
 * {@linkplain Card#RED red} cards that the player holds in their hand).
 */
public class CardView extends StackPane {
    /**
     * Constant used to indicate that there are no {@link Card Cards} of
     * the type of card that the view represents.
     */
    static final int NO_CARDS = -1;

    /**
     * The CSS used to style the font that displays the number of cards.
     */
    private static final String FONT_CSS =
            "-fx-font: 48px Monospace;" +
            "-fx-font-weight: bold;" +
            "-fx-fill: white;" +
            "-fx-stroke: black;" +
            "-fx-stroke-width: 1;";

    /**
     * The {@link CardImage} that determines the {@link Card} type and image
     * to use when displaying the card.
     */
    private CardImage cardImage;

    /**
     * The {@link ImageView} used to display the card's image.
     */
    private final ImageView image;

    /**
     * The {@link Text} used to display the number of cards.
     */
    private final Text number;

    /**
     * Creates a new {@linkplain CardView} to display a specific kind of
     * {@link Card}.
     *
     * @param cardImage Determines the {@link Card} type and image used to
     *                 display the card.
     */
    CardView(CardImage cardImage) {
        this.cardImage = cardImage;

        image = new ImageView();
        image.setImage(cardImage.getImage());

        number = new Text();
        number.setStyle(FONT_CSS);
        setCardCount(NO_CARDS);

        getChildren().addAll(image, number);
    }

    /**
     * Returns the {@link Card} type for this view.
     *
     * @return The {@link Card} type for this view.
     */
    public Card getCard() {
        return cardImage.getCard();
    }

    /**
     * In some cases a card view may be used to display more than one
     * kind of {@linkplain Card card}, e.g. the views used to display the
     * cards dealt to the {@linkplain model.Player player} at the start of a
     * turn. This method is used to change the image on an existing card view.
     *
     * @param cardImage The new {@link CardImage} that the card view should
     *                  display.
     */
    void setCardImage(CardImage cardImage) {
        this.cardImage = cardImage;
    }

    /**
     * Sets the number of {@linkplain Card cards} to display. If the value is
     * less than 0, no number will be displayed on the card view.
     *
     * @param numberOfCards The number of cards to display.
     */
    void setCardCount(int numberOfCards) {
        String text = numberOfCards > 0 ?
                Integer.toString(numberOfCards) : "";
        number.setText(text);
    }

    /**
     * Toggles the card view so that the {@linkplain Card card} appears to be
     * face down. This will show the {@link Card#BACK back} of the card and
     * will not display a number. This is used to avoid revealing information
     * about what cards a {@linkplain model.Player player} has in hand when
     * more than one player is playing on the same computer.
     */
    public void placeFaceDown() {
        image.setImage(CardImage.BACK.getImage());
        number.setVisible(false);
    }

    /**
     * Toggles the card view so that the {@linkplain Card card} appears to be
     * face up. This will show the type and, if appropriate, number of cards
     * of that type. This is used when a {@linkplain model.Player} that is
     * playing on the same computer as other players wants to start a turn and
     * reveal the cards in hand once the other player's aren't looking.
     */
    public void placeFaceUp() {
        image.setImage(cardImage.getImage());
        number.setVisible(true);
    }
}
