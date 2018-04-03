package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.RailroadBaronsException;

import java.io.File;
import java.util.List;

/**
 * The main entry point for the Railroad Barons UI.
 */
public class RailroadBaronsUI extends Application
        implements RailroadBaronsView {

    /**
     * The title displayed in the title bar of the main stage.
     */
    private static final String TITLE =
            "Railroad Barons! A Totally Original Game.";

    /**
     * The usage error message if the game is executed with the wrong number
     * of arguments.
     */
    private static final String USAGE =
            "Usage: java view.RailroadBaronsIO " +
            "<MapMaker Class> <RailroadBarons Class> [Optional Map File]";

    /**
     * The {@link FileChooser} used to load or save map files.
     */
    private final FileChooser chooser;

    /**
     * The {@link RailroadBaronsController} used to facilitate communication
     * to and from the model.
     */
    private final RailroadBaronsController controller;

    /**
     * An {@link Alert} that is used to display error messages.
     */
    private final Alert errorAlert;

    /**
     * The primary {@link Stage} in which the Railroad Barons used interface
     * is updated.
     */
    private Stage primaryStage;

    /**
     * Creates the Railroad Barons UI.
     */
    public RailroadBaronsUI() {
        // this should be in a class...
        chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
            new ExtensionFilter("Railroad Barons Maps (*.rbmap)", "*.rbmap"),
            new ExtensionFilter("All Files (*)", "*"));
        chooser.setInitialFileName("map");
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        controller = new RailroadBaronsController();

        errorAlert = new Alert(Alert.AlertType.ERROR);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<String> params = getParameters().getRaw();
        if(params.size() < 2 || params.size() > 3) {
            System.err.println(USAGE);
            System.exit(1);
        }

        try {
            controller.loadMapMaker(params.get(0));
            controller.loadRailroadBaronsGame(params.get(1));
            if(params.size() > 2) {
                controller.loadMap(params.get(2));
            }
        }
        catch(RailroadBaronsException rbe) {
            System.err.println(rbe.getMessage());
            System.exit(1);
        }

        this.primaryStage = primaryStage;

        primaryStage.setTitle(TITLE);

        BorderPane main = new BorderPane();
        main.setBackground(LIGHT_BROWN_BACKGROUND);

        main.setRight(controller.getPlayerControl());

        RailroadMapPane mapPane = controller.getRailroadMapPane();
        main.setCenter(controller.getRailroadMapPane());
        BorderPane.setAlignment(mapPane, Pos.CENTER);

        main.setTop(buildMenu());

        TextAreaConsole console = controller.getConsole();
        main.setBottom(console);

        Scene scene = new Scene(main);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Builds the {@linkplain MenuBar menu bar} used by the Railroad Barons
     * users to load and save maps.
     *
     * @return The Railroad Barons {@link MenuBar}.
     */
    private MenuBar buildMenu() {
        MenuBar menu = new MenuBar();

        // file menu
        Menu file = new Menu("File");
        // start game option
        MenuItem startAGame = new MenuItem("Start a Game...");
        startAGame.addEventHandler(ActionEvent.ACTION, e -> {
            startAGame();
        });

        file.getItems().add(startAGame);
        menu.getMenus().add(file);

        return menu;
    }

    /**
     * Starts a new game by first prompting the user to open a map file
     * using the {@linkplain FileChooser file chooser}, loads the map into
     * the UI using the {@linkplain RailroadBaronsController controller}, and
     * sizes the stage to fit the new map.
     */
    private void startAGame() {
        File selected = chooser.showOpenDialog(primaryStage);
        if(selected != null) {
            try {
                controller.loadMap(selected.getAbsolutePath());
                primaryStage.sizeToScene();
            }
            catch (RailroadBaronsException e) {
                errorAlert.setTitle("Failed to load map!");
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        }
    }
}
