package view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.*;

/**
 * The deck pane displays all of the information that a
 * {@linkplain Player player} needs to know about number of
 * {@linkplain Card cards} remaining in the {@linkplain Deck deck} any cards
 * dealt at the start of the player's turn.
 */
public class DeckPane extends BorderPane implements RailroadBaronsView {
    /**
     * Initial string of text displayed in lieu of {@link Player} information
     * before the start of a game.
     */
    private static final String GAME_NOT_STARTED = "Game Not Started";

    /**
     * A {@link Text} that is used to display the {@link Player Player's} name
     * and color.
     */
    private final Text playerInfo;

    /**
     * The {@link CardView} used to display the number of cards remaining in
     * the game {@link Deck}.
     */
    private final CardView deck;

    /**
     * The {@link CardView} used to display the first card in the {@link Pair}
     * that was dealt to the {@link Player} at the start of their turn.
     */
    private final CardView cardOne;

    /**
     * The {@link CardView} used to display the second card in the {@link Pair}
     * that was dealt to the {@link Player} at the start of their turn.
     */
    private final CardView cardTwo;

    /**
     * Creates a new deck pane with default settings.
     */
    DeckPane() {
        setPadding(DEFAULT_INSETS);
        setBackground(LIGHT_BROWN_BACKGROUND);

        // shows the name of the current player; visible to start
        playerInfo = new Text();
        playerInfo.setStyle(RailroadBaronsView.getFontCss(WHITE));
        playerInfo.setText(GAME_NOT_STARTED);
        BorderPane.setAlignment(playerInfo, Pos.CENTER);
        setTop(playerInfo);

        // shows the player's piece image and the number of pieces
        GridPane main = new GridPane();
        BorderPane left = new BorderPane();

        // shows the number of cards in the deck
        deck = makeCardView();
        deck.placeFaceUp();
        deck.setVisible(true);
        deck.setCardCount(999);
        main.add(deck, 0, 0);

        // shows the first of the two most recently drawn cards
        cardOne = makeCardView();
        main.add(cardOne, 1, 0);
        // show the second of the two most recently drawn cards
        cardTwo = makeCardView();
        main.add(cardTwo, 2, 0);

        // each grid column is 1/3rd the width
        ColumnConstraints oneThird = new ColumnConstraints();
        oneThird.setPercentWidth(100.0/3.0);
        main.getColumnConstraints().addAll(oneThird, oneThird, oneThird);

        setCenter(main);
    }

    /**
     * Updates the number of {@linkplain Card cards} remaining in the game
     * {@linkplain Deck deck}. This number is displayed to all
     * {@linkplain Player players}.
     *
     * @param numberOfCardsRemaining The number of cards remaining in the
     *                               current game.
     */
    void setNumberOfCardsRemaining(int numberOfCardsRemaining) {
        deck.setCardCount(numberOfCardsRemaining);
    }

    /**
     * Called whenver the information displayed should be changed to match the
     * specified {@linkplain Player player}, e.g. at the start of the player's
     * turn (to show the {@linkplain Pair pair} of cards dealt to the player.
     *
     * @param player The {@link Player} that should be used to update the
     *               display.
     */
    void updateWithPlayer(Player player) {
        cardOne.setVisible(true);
        cardTwo.setVisible(true);

        String fontCss = RailroadBaronsView.getFontCss(WHITE);
        Baron baron = player.getBaron();
        switch(baron) {
            case BLUE:
                fontCss = RailroadBaronsView.getFontCss(BLUE);
                break;
            case GREEN:
                fontCss = RailroadBaronsView.getFontCss(GREEN);
                break;
            case RED:
                fontCss = RailroadBaronsView.getFontCss(RED);
                break;
            case YELLOW:
                fontCss = RailroadBaronsView.getFontCss(YELLOW);
                break;
        }
        playerInfo.setStyle(fontCss);
        playerInfo.setText(baron.toString() + " Player");

        Pair lastDealt = player.getLastTwoCards();
        cardOne.setCardImage(
                CardImage.CARD_IMAGES.get(lastDealt.getFirstCard()));

        cardTwo.setCardImage(
                CardImage.CARD_IMAGES.get(lastDealt.getSecondCard()));
    }

    /**
     * Places the {@linkplain CardView card views} used to display the
     * {@linkplain Pair pair} of {@linkplain Card cards} dealt to the
     * {@linkplain Player player} at the start of their turn
     * {@linkplain CardView#placeFaceDown() face down}, hiding them from other
     * players that are playing on the same computer.
     */
    void placeFaceDown() {
        cardOne.placeFaceDown();
        cardTwo.placeFaceDown();
    }

    /**
     * Places the {@linkplain CardView card views} used to display the
     * {@linkplain Pair pair} of {@linkplain Card cards} dealt to the
     * {@linkplain Player player} at the start of their turn
     * {@linkplain CardView#placeFaceUp() face up} revealing them to the
     * player that is currently playing once no other players are looking at
     * the screen.
     */
    void placeFaceUp() {
        cardOne.placeFaceUp();
        cardTwo.placeFaceUp();
    }

    /**
     * Helper method that makes a new {@linkplain CardView card view} with
     * default settings for the deck pane including:
     * <ul>
     *     <li>Uses the {@linkplain CardImage#BACK card back} image.</li>
     *     <li>Displays no card number.</li>
     *     <li>Is invisible.</li>
     * </ul>
     *
     * @return A new {@link CardView} with the default settings for a deck
     * pane.
     */
    private static CardView makeCardView() {
        CardView view = new CardView(CardImage.BACK);
        view.placeFaceDown();
        view.setCardCount(CardView.NO_CARDS);
        view.setVisible(false);
        return view;
    }
}
