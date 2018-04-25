package student;

import model.*;

import java.util.ArrayList;
import java.util.Collection;

public class MyComputer extends MyPlayer{
    private LonelyEditionBarons game;

    /**
     * Creates a new player and initilize it
     * @param baron
     */
    public MyComputer(Baron baron, LonelyEditionBarons game){
        super(baron);
        this.game = game;
    }

    /**
     * Used to start the player's next turn. A {@linkplain Pair pair of cards}
     * is dealt to the player, and the player is once again able to claim a
     * {@linkplain Route route} on the {@linkplain RailroadMap map}.
     *
     * @param dealt a {@linkplain Pair pair of cards} to the player. Note that
     * one or both of these cards may have a value of {@link Card#NONE}.
     */
    @Override
    public void startTurn(Pair dealt) {
        super.startTurn(dealt);

    }

}
