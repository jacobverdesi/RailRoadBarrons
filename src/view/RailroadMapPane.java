package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.*;

/**
 * A view that displays a {@link RailroadMap Railroad Map}.
 */
public class RailroadMapPane extends GridPane implements RailroadBaronsView {

    /**
     * The {@link RailroadBaronsController controller} that facilitates
     * communication to and from the {@link model}.
     */
    private final RailroadBaronsController controller;

    /**
     * The {@link RailroadMap} that is currently being displayed.
     */
    private RailroadMap map;

    /**
     * The {@link SpacePane SpacePanes} that display the {@link Space spaces}
     * on the current {@link RailroadMap}.
     */
    private SpacePane[][] spaces;

    /**
     * Creates a new railroad map pane that uses the specified
     * {@linkplain RailroadBaronsController controller} to communicate with
     * the {@linkplain model}.
     *
     * @param controller The {@link RailroadBaronsController} that is used to
     *                   facilitate communication to and from the
     *                   {@link model}.
     */
    RailroadMapPane(RailroadBaronsController controller) {
        this.controller = controller;

        setPadding(DEFAULT_INSETS);
        setBackground(BLACK_BACKGROUND);
    }

    /**
     * Sets the {@linkplain RailroadMap map} that is currently displayed to
     * the specified map.
     *
     * @param map The {@link RailroadMap} to display.
     */
    void setRailroadMap(RailroadMap map) {
        this.map = map;
        if(map != null) {
            setBackground(LIGHT_BROWN_BACKGROUND);
            getChildren().clear();
            int rows = map.getRows();
            int cols = map.getCols();
            spaces = new SpacePane[rows][cols];

            for(int row=0; row<rows; row++) {
                for(int col=0; col<cols; col++) {
                    Space space = map.getSpace(row, col);
                    SpacePane pane;

                    if(space instanceof Track) {
                        Track track = (Track)space;
                        pane = makeTrackPane(track);
                    }
                    else if(space instanceof Station) {
                        Station station = (Station)space;
                        pane = makeStationPane(station);
                    }
                    else {
                        pane = new SpacePane(SpaceImage.EMPTY);
                    }

                    add(pane, col, row);
                    spaces[row][col] = pane;
                }
            }
        }
    }

    /**
     * Called when a {@linkplain Route route} has been claimed on the current
     * {@link RailroadMap} map.
     *
     * @param route The {@link Route} that has been claimed.
     */
    void routeClaimed(Route route) {
        for(Track track : route.getTracks()) {
            spaces[track.getRow()][track.getCol()].claim(route.getBaron());
        }
    }

    /**
     * Handles the work of claiming the {@linkplain Route route} that contains
     * the {@linkplain Track track} at the specified location. First verifies
     * that the route can be claimed by the current player, and then uses the
     * {@linkplain RailroadBaronsController controller} to
     * {@linkplain RailroadBaronsController#claimRoute(int, int) claim the
     * route}.
     *
     * @param row The row of one of the {@link Track Tracks} in the
     *            {@link Route} that is being claimed.
     * @param col The col of one of the {@link Track Tracks} in the
     *            {@link Route} that is being claimed.
     */
    private void claimRoute(int row, int col) {
        if(controller.canClaimRoute(row, col)) {
            try {
                controller.claimRoute(row, col);
            } catch (RailroadBaronsException e) {
                showAlert("Error!", e.getMessage());
            }
        }
        else {
            showAlert("Cannot Claim Route!",
                    "You can't claim that route. Make sure that you have " +
                            "enough pieces and cards!");
        }
    }

    /**
     * Displays an {@linkplain Alert alert} with the specified title and
     * content.
     *
     * @param title The title for the error {@link Alert}.
     * @param content The content to display in the {@link Alert} dialog.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Helper method that creates a {@linkplain SpacePane pane} to display the
     * specified {@linkplain Station station} on the current
     * {@linkplain RailroadMap map}. Adds a {@linkplain Tooltip tooltip} that
     * will display the station's name when moused over.
     *
     * @param station The {@link Station} for which a {@link SpacePane} is to
     *                be created.
     * @return The {@link SpacePane} used to display the specified
     * {@link Station}.
     */
    private SpacePane makeStationPane(Station station) {
        SpacePane pane = new SpacePane(SpaceImage.STATION);
        Tooltip tooltip = new Tooltip(station.getName());
        Tooltip.install(pane, tooltip);
        return pane;
    }

    /**
     * Helper method that creates a {@linkplain SpacePane pane} to display the
     * specified {@linkplain Track track} on the current
     * {@linkplain RailroadMap map}.
     *
     * @param track The {@link Track} for which a {@link SpacePane} is to
     *                be created.
     * @return The {@link SpacePane} used to display the specified
     * {@link Track}.
     */
    private SpacePane makeTrackPane(final Track track) {
        Orientation orientation = track.getOrientation();
        SpacePane pane = new SpacePane(orientation == Orientation.HORIZONTAL ?
                SpaceImage.TRACK_HORIZONTAL :
                SpaceImage.TRACK_VERTICAL);

        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            claimRoute(track.getRow(), track.getCol());
        });

        return pane;
    }
}
