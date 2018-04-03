package view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import model.Baron;

/**
 * Interface that contains useful constants for various view classes.
 */
public interface RailroadBaronsView {
    /**
     * Color constant for blue.
     */
    String BLUE = "blue";

    /**
     * Color constant for green.
     */
    String GREEN = "green";

    /**
     * Color constant for red.
     */
    String RED = "red";

    /**
     * Color constant for white.
     */
    String WHITE = "white";

    /**
     * Color constant for yellow.
     */
    String YELLOW = "yellow";

    /**
    * The CSS used to style the font so that it is centered and has a 1px
    * black stroke.
    */
    String FONT_CSS =
        "-fx-font-weight: bold;" +
        "-fx-stroke: black;" +
        "-fx-stroke-width: 1;";

    /**
     * The default font size.
     */
    int DEFAULT_FONT_SIZE = 24;

    /**
     * Constant for right alignment.
     */
    String RIGHT = "right";

    /**
     * Constant for left alignment.
     */
    String LEFT = "left";

    /**
     * Constant for center alignment.
     */
    String CENTER = "center";

    /**
     * The default alignment (centered).
     */
    String DEFAULT_ALIGNMENT = CENTER;

    /**
     * The default {@link Insets} when insets are needed.
     */
    Insets DEFAULT_INSETS = new Insets(5);

    /**
     * A very tiny {@link Insets}.
     */
    Insets TINY_INSETS = new Insets(2);

    /**
     * A dark brown background color.
     */
    Background DARK_BROWN_BACKGROUND =
            new Background(new BackgroundFill(Color.BROWN.darker(),
                    CornerRadii.EMPTY, Insets.EMPTY));

    /**
     * A light brown background color.
     */
    Background LIGHT_BROWN_BACKGROUND =
            new Background(new BackgroundFill(Color.WHEAT,
                    CornerRadii.EMPTY, Insets.EMPTY));

    /**
     * A black background color.
     */
    Background BLACK_BACKGROUND =
            new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,
                    Insets.EMPTY));

    /**
     * Helper method that returns a valid CSS color constant for a given
     * {@linkplain Baron}.
     * @param baron The {@link Baron} for whom to fetch the appropriate color
     *              constant.
     * @return A valid CSS color constant for the given {@link Baron}.
     */
    static String baronToColor(Baron baron) {
        String color;
        switch(baron) {
            case RED:
                color = RED;
                break;
            case BLUE:
                color = BLUE;
                break;
            case GREEN:
                color = GREEN;
                break;
            case YELLOW:
                color = YELLOW;
                break;
            default:
                color = WHITE;
                break;
        }
        return color;
    }

    /**
     * Returns the CSS to style a font that uses the
     * {@link #DEFAULT_FONT_SIZE default size} and specified color.
     * @param color The color for the font style.
     * @return The CSS for a font that uses the default size and specified
     * color.
     */
    static String getFontCss(String color) {
        return getFontCss(DEFAULT_FONT_SIZE, color, DEFAULT_ALIGNMENT);
    }

    /**
     * Returns the CSS to style a font that uses the specified size and color.
     * @param size The size for the font style (in pixels).
     * @param color The color for the font style.
     * @return The CSS for a font that uses the specified size and color.
     */
    static String getFontCss(int size, String color) {
        return getFontCss(size, color, DEFAULT_ALIGNMENT);
    }

    static String getFontCss(int size, String color, String alignment) {
        return "-fx-font: " + Integer.toString(size) + "px Monospace;" +
                "-fx-alignment: " + alignment + ";" +
                FONT_CSS +
                "-fx-fill: " + color + ";";
    }
}
