package view;

import javafx.scene.image.Image;
import model.Card;

import java.util.HashMap;
import java.util.Map;

/**
 * An enum used to determine which image to use when displaying
 * {@linkplain Card cards} in the Railroad Barons UI. Associates a specific
 * image with each type of card.
 */
public enum CardImage {
    NONE(Card.NONE, "none.png"),
    BACK(Card.BACK, "back.png"),
    BLACK(Card.BLACK, "black.png"),
    BLUE(Card.BLUE, "blue.png"),
    GREEN(Card.GREEN, "green.png"),
    ORANGE(Card.ORANGE, "orange.png"),
    PINK(Card.PINK, "pink.png"),
    RED(Card.RED, "red.png"),
    WHITE(Card.WHITE, "white.png"),
    WILD(Card.WILD, "wild.png"),
    YELLOW(Card.YELLOW, "yellow.png");

    /**
     * A {@linkplain Map map} of {@linkplain Card cards} to
     * {@linkplain CardImage card images} used for easy lookup.
     */
    public static final Map<Card, CardImage> CARD_IMAGES = new HashMap<>();
    static {
        CARD_IMAGES.put(Card.NONE, NONE);
        CARD_IMAGES.put(Card.BLACK, BLACK);
        CARD_IMAGES.put(Card.BLUE, BLUE);
        CARD_IMAGES.put(Card.GREEN, GREEN);
        CARD_IMAGES.put(Card.ORANGE, ORANGE);
        CARD_IMAGES.put(Card.PINK, PINK);
        CARD_IMAGES.put(Card.RED, RED);
        CARD_IMAGES.put(Card.WHITE, WHITE);
        CARD_IMAGES.put(Card.WILD, WILD);
        CARD_IMAGES.put(Card.YELLOW, YELLOW);
    }

    /**
     * Path to the directory that contains the card images.
     */
    private static final String IMAGE_PATH = "file:media/cards/";

    /**
     * The {@link Card} associated with the card image.
     */
    private final Card card;

    /**
     * The {@link Image} that should be used to display a card of the
     * specified type.
     */
    private final Image image;

    /**
     * Creates a new {@linkplain CardImage} with the specified
     * {@linkplain Card card} and image name.
     * @param card The type of card that needs to be displayed {@link Card}.
     * @param imageName The name of the image to use when displaying cards
     *                  of this type.
     */
    CardImage(Card card, String imageName) {
        this.card = card;
        image = new Image(IMAGE_PATH + imageName);
    }

    /**
     * Returns the {@linkplain Card card} associated with this card image.
     *
     * @return The {@link Card}.
     */
    public Card getCard() {
        return card;
    }

    /**
     * Returns the {@link Image} used to display the {@link Card} associated
     * with this card image.
     *
     * @return The {@link Image}.
     */
    public Image getImage() {
        return image;
    }
}
