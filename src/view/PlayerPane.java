package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Baron;
import model.Orientation;
import model.Player;

/**
 * Displays information about each {@linkplain Player player} that the other
 * players are allowed to see; namely the number of pieces that the player has
 * remaining and the player's current score.
 */
public class PlayerPane extends GridPane implements RailroadBaronsView {
    /**
     * Font size used to display {@link Player} name and score.
     */
    private static final int FONT_SIZE = 23;

    /**
     * The {@link Text} used to display the {@link Player player's} score.
     */
    private final Text score;

    /**
     * The {@link Text} used to display the {@link Player player's} number of
     * remaining pieces.
     */
    private final Text pieceCount;

    /**
     * Creates a new player pane to display the current public state of the
     * player that uses the specified {@linkplain Baron} to play.
     *
     * @param baron The {@link Baron} used by the {@link Player} about whom
     *              this player pane will display information.
     */
    PlayerPane(Baron baron) {
        setBackground(LIGHT_BROWN_BACKGROUND);
        setPadding(DEFAULT_INSETS);
        Text name = new Text();
        name.setStyle(RailroadBaronsView.getFontCss(FONT_SIZE,
                RailroadBaronsView.baronToColor(baron)));
        name.setText(baron.toString());

        score = new Text();
        score.setStyle(RailroadBaronsView.getFontCss(FONT_SIZE, WHITE));
        score.setText("0");

        ImageView piece = new ImageView(PieceImage.PIECE_IMAGES.get(baron)
                .getImage(Orientation.HORIZONTAL));

        pieceCount = new Text();
        pieceCount.setStyle(RailroadBaronsView.getFontCss(FONT_SIZE, WHITE));
        pieceCount.setText("0");

        ColumnConstraints nameConstraints = new ColumnConstraints();
        nameConstraints.setPercentWidth(66);
        ColumnConstraints scoreConstraints = new ColumnConstraints();
        scoreConstraints.setPercentWidth(34);
        getColumnConstraints().addAll(nameConstraints, scoreConstraints);

        add(name, 0, 0);
        add(score, 1, 0);
        add(piece, 0, 1);
        add(pieceCount, 1, 1);
    }

    /**
     * Updates the information displayed based on the
     * {@linkplain Player player's} current state.
     *
     * @param player The {@link Player} that has been changed.
     */
    public void updateWithPlayer(Player player) {
        score.setText(Integer.toString(player.getScore()));
        pieceCount.setText(Integer.toString(player.getNumberOfPieces()));
    }
}
