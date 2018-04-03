package view;

import javafx.scene.image.Image;
import model.Baron;
import model.Orientation;

import java.util.HashMap;
import java.util.Map;

/**
 * An enum used to determine which {@linkplain Image image} to use when
 * displaying train pieces in the Railroad Barons UI. Associates two images
 * with each color of piece: one for
 * {@linkplain Orientation#HORIZONTAL horizontal} pieces, and one for
 * {@linkplain Orientation#VERTICAL vertical} pieces.
 */
public enum PieceImage {
    NONE("none.png", "none.png"),
    BLUE("blue_horizontal.png", "blue_vertical.png"),
    GREEN("green_horizontal.png", "green_vertical.png"),
    RED("red_horizontal.png", "red_vertical.png"),
    YELLOW("yellow_horizontal.png", "yellow_vertical.png");

    /**
     * A {@linkplain Map map} of {@linkplain Baron Barons} to piece images
     * that may be used for easy lookup.
     */
    public static final Map<Baron, PieceImage> PIECE_IMAGES = new HashMap<>();
    static {
        PIECE_IMAGES.put(Baron.UNCLAIMED, NONE);
        PIECE_IMAGES.put(Baron.BLUE, BLUE);
        PIECE_IMAGES.put(Baron.GREEN, GREEN);
        PIECE_IMAGES.put(Baron.RED, RED);
        PIECE_IMAGES.put(Baron.YELLOW, YELLOW);
    }

    /**
     * The path to the location of the images used to display pieces.
     */
    private static final String IMAGE_PATH = "file:media/pieces/";

    /**
     * The {@link Orientation#HORIZONTAL horizontal} {@link Image} used for
     * the train piece.
     */
    private final Image horizontalImage;

    /**
     * The {@link Orientation#VERTICAL vertical} {@link Image} used for the
     * train piece.
     */
    private final Image verticalImage;

    /**
     * Creates a new piece image with the specified
     * {@linkplain Orientation#HORIZONTAL} and
     * {@linkplain Orientation#VERTICAL} images.
     *
     * @param horizontalImageName The name of the
     * {@link Orientation#HORIZONTAL} image for this piece.
     * @param verticalImageName The name of the {@link Orientation#VERTICAL}
     *                          image for this piece.
     */
    PieceImage(String horizontalImageName, String verticalImageName) {
        horizontalImage = new Image(IMAGE_PATH + horizontalImageName);
        verticalImage = new Image(IMAGE_PATH + verticalImageName);
    }

    /**
     * Returns the appropriate {@linkplain Image image} to display this piece
     * in the specified {@linkplain Orientation orientation}.
     *
     * @param orientation The {@link Orientation} that determines which image
     *                    is returned for this piece.
     * @return The {@link Image} to use to display the piece in the specified
     * {@link Orientation}.
     */
    public Image getImage(Orientation orientation) {
        return orientation == Orientation.VERTICAL ?
                verticalImage :
                horizontalImage;
    }
}
