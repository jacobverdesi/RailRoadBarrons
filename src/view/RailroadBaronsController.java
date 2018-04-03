package view;

import javafx.scene.control.Alert;
import model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static java.lang.Class.forName;

/**
 * The main controller in the Railroad Barons GUI. In the Railroad Barons
 * implementation of MVC, there is only one controller class and it is
 * tightly coupled with both the model and the view. Rather than place it in
 * its own package (where it would be the sole class), it is located in the
 * view package.
 */
public class RailroadBaronsController implements RailroadMapObserver,
        RailroadBaronsObserver, PlayerObserver {
    /**
     * Displays the current status of the game to the player including the
     * score board, deck/dealt cards, and the player's hand.
     */
    private final PlayerControlPane playerControl;

    /**
     * Displays the {@link RailroadMap}.
     */
    private final RailroadMapPane railroadMapPane;

    /**
     * An implementation of the {@link Console} interface that appends lines
     * of text to a scrolling {@link javafx.scene.control.TextArea}.
     */
    private final TextAreaConsole console;

    /**
     * The implementation of the {@link MapMaker} interface that is used to
     * save and load {@link RailroadMap RailroadMaps} when a game is started
     * or saved. The controller loads the specific implementation by class
     * name.
     */
    private MapMaker mapMaker;

    /**
     * The implementation of the {@link RailroadBarons} interface that is ued
     * to run games. The controller loads the specific implementation by class
     * name.
     */
    private RailroadBarons game;

    /**
     * Creates a new controller for use by the Railroad Barons UI. The
     * controller also creates and configured most of the essential parts of
     * the user interface.
     */
    RailroadBaronsController() {
        playerControl = new PlayerControlPane(this);
        railroadMapPane = new RailroadMapPane(this);
        console = new TextAreaConsole();
        console.setStyle(RailroadBaronsView.getFontCss(12, "black"));
    }

    /**
     * Loads an implementation of the {@linkplain MapMaker map maker}
     * interface that will henceforth be used to load and save the maps on
     * which games of Railroad Barons are played. The following requirements
     * must be satisfied:
     * <ul>
     *     <li>The specified class name is fully qualified, i.e. contains the
     *     package as well as the class name, e.g. student.MyMapMaker</li>
     *     <li>The specified class implements the MapMaker interface in the
     *     Railroad Barons {@link model model package}.</li>
     *     <li>The specified class has a default or parameterless
     *     constructor.</li>
     *     <li>The class and its constructor must be accessible from outside
     *     of its package (i.e. public)</li>
     * </ul>
     *
     * @param className The name of a class that implements the
     * {@link MapMaker} interface.
     *
     * @throws RailroadBaronsException If there is a problem loading the class
     * (i.e. it does not meet the aforementioned requirements).
     */
    void loadMapMaker(String className) throws RailroadBaronsException {
        mapMaker = loadGameClass(className, MapMaker.class);
    }

    /**
     * Loads an implementation of the
     * {@linkplain RailroadBarons Railroad Barons} interface that will
     * henceforth be used play Railroad Barons. The following requirements
     * must be satisfied:
     * <ul>
     *     <li>The specified class name is fully qualified, i.e. contains the
     *     package as well as the class name, e.g.
     *     student.MyRailroadBarons</li>
     *     <li>The specified class implements the RailroadBarons interface in
     *     the Railroad Barons {@link model model package}.</li>
     *     <li>The specified class has a default or parameterless
     *     constructor.</li>
     *     <li>The class and its constructor must be accessible from outside
     *     of its package (i.e. public)</li>
     * </ul>
     *
     * The controller will add itself as an
     * {@linkplain RailroadBaronsObserver observer} to the newly created
     * instance.
     *
     * @param className The name of a class that implements the
     * {@link RailroadBarons} interface.
     *
     * @throws RailroadBaronsException If there is a problem loading the class
     * (i.e. it does not meet the aforementioned requirements).
     */
    void loadRailroadBaronsGame(String className)
            throws RailroadBaronsException {
        game = loadGameClass(className, RailroadBarons.class);
        game.addRailroadBaronsObserver(this);
        for(Player player : game.getPlayers()) {
            player.addPlayerObserver(this);
        }
    }

    /**
     * Returns the {@linkplain PlayerControlPane player control pane} used by
     * the Railroad Barons UI to display information to the current
     * {@linkplain Player player}, and allow the player to start and end a
     * turn.
     *
     * @return The {@link PlayerControlPane} used by the Railroad Barons UI.
     */
    PlayerControlPane getPlayerControl() {
        return playerControl;
    }

    /**
     * Returns the {@linkplain RailroadMapPane railroad map pane} used by the
     * Railroad Barons UI to display the map on which the current game is
     * being played.
     *
     * @return The {@link RailroadMapPane} that is used by the Railroad Barons
     * UI.
     */
    RailroadMapPane getRailroadMapPane() {
        return railroadMapPane;
    }

    /**
     * Returns the {@linkplain TextAreaConsole console} used to display
     * textual information to the human user such as which routes have been
     * claimed and by whom.
     *
     * @return The {@link TextAreaConsole} that is used to display information
     * to the human user.
     */
    TextAreaConsole getConsole() { return console; }

    /**
     * Used to end the current {@linkplain Player player's} turn. This is
     * called from the {@linkplain PlayerControlPane player control pane} when
     * the current player presses the button to end the turn.
     */
    void endTurn() {
        game.endTurn();
    }

    /**
     * Loads the {@linkplain RailroadMap map} to which the specified path
     * refers and uses it to start a new game of Railroad Barons!
     *
     * @param path The path to the Railroad Barons map file that should be
     *             loaded to start a new game.
     *
     * @throws RailroadBaronsException If loading a {@link RailroadMap} from
     * the specified file fails for any reason.
     */
    void loadMap(String path) throws RailroadBaronsException {
        if(mapMaker == null) {
            throw new RailroadBaronsException(
                    "MapMaker has not been configured!");
        }

        try(InputStream in = new FileInputStream(path)) {
            RailroadMap map = mapMaker.readMap(in);
            console.write("Loaded map '" + path + "'!");
            map.addObserver(this);
            game.startAGameWith(map);
            railroadMapPane.setRailroadMap(map);
            playerControl.setNumberOfCardsRemaining(
                    game.numberOfCardsRemaining());
        }
        catch(IOException ioe) {
            throw new RailroadBaronsException("Failed to load map: " +
                    ioe.getMessage());
        }
    }

    /**
     * Tests whether or not the current {@linkplain Player player} can claim
     * the {@linkplain Route route} at the specified location.
     *
     * @param row The row of a {@link Track} in the {@link Route} that the
     *            current {@link Player} is attempting to claim.
     * @param col The col of a {@link Track} in the {@link Route} that the
     *            current {@link Player} is trying to claim.
     * @return True if the current {@link Player} can claim the {@link Route}
     * at the specified location, and false otherwise.
     */
    boolean canClaimRoute(int row, int col) {
        return game.canCurrentPlayerClaimRoute(row, col);
    }

    /**
     * Attempts to claim the {@linkplain Route route} at the specified
     * location on the {@linkplain RailroadMap map} on behalf of the current
     * {@linkplain Player player}. This method may throw an exception! The
     * route should be checked for {@linkplain #canClaimRoute(int, int)
     * claimability} before calling this method.
     *
     * @param row The row of a {@link Track} in the {@link Route} that the
     *            current {@link Player} is attempting to claim.
     * @param col The col of a {@link Track} in the {@link Route} that the
     *            current {@link Player} is trying to claim.
     *
     * @throws RailroadBaronsException If the {@link Route} cannot be claimed,
     * e.g. no such route exists or the {@link Route} has already been
     * claimed.
     */
    void claimRoute(int row, int col) throws RailroadBaronsException {
        game.claimRoute(row, col);
    }

    //
    // map observer methods
    //

    /**
     * Updates the Railroad Barons UI in response to a newly claimed
     * {@linkplain Route route}.
     *
     * @param map The {@link RailroadMap} on which the {@link Route} has been
     *            claimed.
     *
     * @param route The {@link Route} that has been claimed.
     */
    @Override
    public void routeClaimed(RailroadMap map, Route route) {
        playerControl.setNumberOfCardsRemaining(game.numberOfCardsRemaining());
        railroadMapPane.routeClaimed(route);
        console.write(game.getCurrentPlayer().toString() +
                " has claimed the route from " + route.getOrigin().getName()
                + " to " + route.getDestination().getName() + " earning "
                + route.getPointValue() + " points!");
    }

    //
    // game observer methods
    //

    /**
     * Updates the {@linkplain PlayerControlPane player control pane} in
     * response to the start of a {@linkplain Player player's} turn.
     *
     * @param game The {@link RailroadBarons} game for which a new turn has
     *             started.
     * @param player The {@link Player} that has just started a turn.
     */
    @Override
    public void turnStarted(RailroadBarons game, Player player) {
        playerControl.startTurn(player);
        playerControl.setNumberOfCardsRemaining(
                game.numberOfCardsRemaining());
        console.write(player.toString() + " has started their turn!");
    }

    /**
     * Updates the {@linkplain PlayerControlPane player control pane} in
     * response to the end of a {@linkplain Player player's} turn.
     *
     * @param game The {@link RailroadBarons} game for which the current turn
     *             has ended.
     * @param player The {@link Player} whose turn has ended.
     */
    @Override
    public void turnEnded(RailroadBarons game, Player player) {
        playerControl.endTurn();
        console.write(player.toString() + " has ended their turn!");
    }

    /**
     * Displays an {@linkplain Alert alert} indicating that the game is over.
     *
     * @param game The {@link RailroadBarons} game that has ended.
     * @param winner The winning {@link Player}.
     */
    @Override
    public void gameOver(RailroadBarons game, Player winner) {
        String message = "The " + winner + " wins with " +
                winner.getScore() + " points!";
        console.write(message);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over!");
        alert.setContentText(message);
        alert.show();
    }

    //
    // player observer methods
    //

    /**
     * Updates the {@linkplain PlayerControlPane player control pane} in
     * response to a change in the state of the specified {@link Player}.
     *
     * @param player The {@link Player} of interest.
     */
    @Override
    public void playerChanged(Player player) {
        playerControl.updateWithPlayer(player);
    }

    /**
     * Helper method that uses Java's reflection mechanism to attempt to load
     * a class with the specified name and return it as in instance of the
     * specified type.
     *
     * @param name The name of the class to attempt to load.
     * @param type The type of class into which the newly loaded class should
     *             be cast.
     * @param <T> The type of class into which the newly loaded class should
     *           be cast.
     * @return The newly loaded class.
     * @throws RailroadBaronsException If the class with the specified name
     * does not exist, is the wrong type, or can't be loaded for any other
     * reason.
     */
    private <T> T loadGameClass(String name, Class<T> type)
            throws RailroadBaronsException {
        try {
            @SuppressWarnings("unchecked")
            Class<T> loaded = (Class<T>)forName(name);
            Constructor<T> constructor = loaded.getConstructor();
            return constructor.newInstance();
        }
        catch (ClassNotFoundException e) {
            throw new RailroadBaronsException("Cannot find the class: "
                + name);
        } catch (NoSuchMethodException e) {
            throw new RailroadBaronsException(
                "Class does not have a public, parameterless constructor: " +
                name);
        } catch (IllegalAccessException e) {
            throw new RailroadBaronsException(
                "Class constructor is not public: " +
                            name);

        } catch (InstantiationException | InvocationTargetException e) {
            throw new RailroadBaronsException(
                "Cannot call constructor: " + name);
        }
    }
}
