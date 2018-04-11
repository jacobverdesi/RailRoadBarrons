package student;

import model.*;

import java.util.Collection;

public class MyPlayer implements Player {

    public MyPlayer(){

    }

    @Override
    public void reset(Card... dealt) {

    }

    @Override
    public void addPlayerObserver(PlayerObserver observer) {

    }

    @Override
    public void removePlayerObserver(PlayerObserver observer) {

    }

    @Override
    public Baron getBaron() {
        return null;
    }

    @Override
    public void startTurn(Pair dealt) {

    }

    @Override
    public Pair getLastTwoCards() {
        return null;
    }

    @Override
    public int countCardsInHand(Card card) {
        return 0;
    }

    @Override
    public int getNumberOfPieces() {
        return 0;
    }

    @Override
    public boolean canClaimRoute(Route route) {
        return false;
    }

    @Override
    public void claimRoute(Route route) throws RailroadBaronsException {

    }

    @Override
    public Collection<Route> getClaimedRoutes() {
        return null;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public boolean canContinuePlaying(int shortestUnclaimedRoute) {
        return false;
    }
}
