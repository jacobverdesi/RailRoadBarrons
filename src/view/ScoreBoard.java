package view;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import model.Baron;
import model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Displays a {@linkplain PlayerPane pane} for each {@linkplain Player player}
 * in the game. Each pane includes the player's name, current score, and
 * color and number of that player's remaining train pieces.
 */
class ScoreBoard extends GridPane implements RailroadBaronsView {
    /**
     * Determines the number of columns into which the player panes should be
     * divided.
     */
    private static final int MAX_COLUMNS = 2;

    /**
     * A {@link Map} of {@link Baron barons} to
     * {@link PlayerPane player panes}. Used to update individual panes with
     * new information as the state of individual players changes.
     */
    private final Map<Baron, PlayerPane> panes;

    /**
     * Creates a new score board.
     */
    ScoreBoard() {
        setBackground(DARK_BROWN_BACKGROUND);
        panes = new HashMap<>();

        ColumnConstraints constraints = new ColumnConstraints();
        constraints.setPercentWidth(50.0);
        for(int i=0; i<MAX_COLUMNS; i++) {
            getColumnConstraints().add(constraints);
        }

        // code that is way fancier than it needs to be, but would
        // theoretically adapt to adding more barons.
        int col = 0;
        int row = 0;
        for(Baron baron : Baron.BARONS) {
            PlayerPane pane = new PlayerPane(baron);
            panes.put(baron, pane);
            setMargin(pane, TINY_INSETS);
            add(pane, col, row);
            col = (col + 1) % MAX_COLUMNS;
            row = col != 0 ? row : row + 1;
        }
    }

    /**
     * Updates the score for a specific {@linkplain Player player}.
     *
     * @param player The {@link Player} for whom the score should be updated.
     */
    void updatePlayerScore(Player player) {
        Baron baron = player.getBaron();
        PlayerPane pane = panes.get(baron);
        pane.updateWithPlayer(player);
    }
}
