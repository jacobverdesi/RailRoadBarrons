package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.Baron;
import model.Orientation;

public class SpacePane extends StackPane {
    private final SpaceImage spaceImage;

    private final ImageView background;
    private final ImageView foreground;

    SpacePane(SpaceImage spaceImage) {
        this.spaceImage = spaceImage;

        background = new ImageView(spaceImage.getImage());
        foreground = new ImageView();
        claim(Baron.UNCLAIMED);

        getChildren().addAll(background, foreground);
    }

    void claim(Baron baron) {
        PieceImage image = PieceImage.PIECE_IMAGES.get(baron);
        switch(spaceImage) {
            case TRACK_HORIZONTAL:
                foreground.setImage(image.getImage(Orientation.HORIZONTAL));
                break;
            case TRACK_VERTICAL:
                foreground.setImage(image.getImage(Orientation.VERTICAL));
                break;
        }
    }
}
