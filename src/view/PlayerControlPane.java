package view;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import model.Player;

/**
 * Displays pretty much everything that the human player needs to see except
 * for the view of the map. This includes the
 * {@linkplain ScoreBoard scoreboard}, {@linkplain DeckPane deck pane}, and
 * {@linkplain PlayerHandPane player handPane pane} as well as the button to
 * start/end turns.
 */
public class PlayerControlPane extends GridPane
        implements RailroadBaronsView {
    /**
     * Button label used when the {@link Player Player's} turn has begun and
     * the {@link model.Card cards} are still face down. The player uses this
     * to flip the cards face up and begin playing.
     */
    private static final String START_TURN = "Start Turn!";

    /**
     * Button label used when the {@link Player} is finished playing and
     * wants to end the turn.
     */
    private static final String END_TURN = "End Turn";

    /**
     * Button label used when the current {@link Player Player's} turn has
     * ended and the player is waiting for the next turn to start. This occurs
     * between the end of one turn and the start of the next. Most of the time
     * the transition will be fast enough that human players won't notice.
     */
    private static final String WAITING = "Waiting...";


    /**
     * The {@link RailroadBaronsController} used to facilitate communication
     * between this view and the model as needed.
     */
    private final RailroadBaronsController controller;

    /**
     * Displays the current status of every {@link Player} including score and
     * remaining train pieces.
     */
    private final ScoreBoard scoreBoard;

    /**
     * Displays information about the number of {@link model.Card cards}
     * remaining in the game deck as well as the most dealt {@link model.Pair}
     * of cards.
     */
    private final DeckPane deckPane;

    /**
     * Displays the current {@link Player Player's} hand of cards.
     */
    private final PlayerHandPane handPane;

    /**
     * Single {@link Button} used to start or end a turn. The button is
     * disabled while waiting between turns (usually instantaneous).
     */
    private final Button turnControlButton;

    /**
     * The {@link Player} currently being displayed by the
     * {@link PlayerControlPane}.
     */
    private Player currentPlayer;

    /**
     * Creates a new player control pane to display information to
     * {@linkplain Player players} of a Railroad Barons game.
     *
     * @param controller The {@link RailroadBaronsController} used to
     *                   facilitate communication to and from the model.
     */
    PlayerControlPane(RailroadBaronsController controller) {
        this.controller = controller;

        setBackground(LIGHT_BROWN_BACKGROUND);

        scoreBoard = new ScoreBoard();
        add(scoreBoard, 0, 0);

        deckPane = new DeckPane();
        add(deckPane, 0, 1);

        handPane = new PlayerHandPane();
        add(handPane, 0, 2);

        turnControlButton = new Button(START_TURN);
        turnControlButton.setOnAction(e -> {
            toggleTurn();
        });
        turnControlButton.setMaxWidth(Double.MAX_VALUE);
        turnControlButton.setVisible(false);
        turnControlButton.setMaxHeight(Double.MAX_VALUE);
        turnControlButton.setStyle(RailroadBaronsView.getFontCss(24, WHITE) +
            "-fx-base: burlywood; -fx-border-color: brown;");
        add(turnControlButton, 0, 3);
        RowConstraints constraints = new RowConstraints();
        constraints.setFillHeight(true);
        constraints.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(new RowConstraints(), new RowConstraints(),
                new RowConstraints(), constraints);

        currentPlayer = null;
    }

    /**
     * This method is called to update the display of the number of
     * {@linkplain model.Card cards} remaining in the game
     * {@linkplain model.Deck deck}.
     *
     * @param numberOfCardsRemaining The number of {@link model.Card Cards}
     *                               remaining in the game
     *                               {@link model.Deck Deck}.
     */
    void setNumberOfCardsRemaining(int numberOfCardsRemaining) {
        deckPane.setNumberOfCardsRemaining(numberOfCardsRemaining);
    }

    /**
     * Called when a {@linkplain Player player} starts a new turn.
     *
     * @param player The {@link Player} that has started a turn.
     */
    void startTurn(Player player) {
        if(player == currentPlayer) {
            updateWithPlayer(player);
            turnControlButton.setText(END_TURN);
            turnControlButton.setDisable(false);
        }
        else {
            currentPlayer = player;
            handPane.placeFaceDown();
            deckPane.placeFaceDown();
            updateWithPlayer(player);
            turnControlButton.setText(START_TURN);
            turnControlButton.setDisable(false);
        }
    }

    /**
     * Ends the turn for the current {@linkplain Player player}.
     */
    void endTurn() {
        turnControlButton.setText(WAITING);
        turnControlButton.setDisable(true);
    }

    /**
     * Updates all of the various components with the latest information about
     * the specified {@linkplain Player player}.
     *
     * @param player The {@link Player} for whom the information should be
     *               updated.
     */
    void updateWithPlayer(Player player) {
        if(player == currentPlayer) {
            deckPane.updateWithPlayer(player);
            handPane.updateWithPlayer(player);
            turnControlButton.setVisible(true);
        }
        scoreBoard.updatePlayerScore(player);
    }

    /**
     * Helper method that toggles the turn control {@link Button}.
     */
    private void toggleTurn() {
        String text = turnControlButton.getText();
        if(text.equals(START_TURN)) {
            handPane.placeFaceUp();
            deckPane.placeFaceUp();
            turnControlButton.setText(END_TURN);
        }
        else {
            controller.endTurn();
        }
    }
}
