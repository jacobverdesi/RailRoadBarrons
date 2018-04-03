package view;

import javafx.scene.image.Image;

/**
 * An enum used to determine which image should be used when displaying a
 * {@linkplain model.Space space} on the {@linkplain model.RailroadMap map}.
 */
public enum SpaceImage {
    EMPTY("empty.png"),
    STATION("station.png"),
    TRACK_VERTICAL("track_vertical.png"),
    TRACK_HORIZONTAL("track_horizontal.png");

    /**
     * The path to the directory that contains the space images.
     */
    private static final String IMAGE_PATH = "file:media/spaces/";

    /**
     * The {@link Image} to use when displaying a space of the corresponding
     * type.
     */
    private final Image image;

    /**
     * Creates a new {@linkplain SpaceImage space image} with the specified
     * image name.
     *
     * @param imageName The name of the image to use when displaying a
     * {@link model.Space} of the corresponding type.
     */
    SpaceImage(String imageName) {
        image = new Image(IMAGE_PATH + imageName);
    }

    /**
     * Returns the {@linkplain Image image} that should be used to display the
     * image for a {@linkplain model.Space space} of the corresponding type.
     * @return The {@link Image} to use to display a {@link model.Space} of
     * the type that corresponds with this {@link SpaceImage}.
     */
    public Image getImage() {
        return image;
    }
}
