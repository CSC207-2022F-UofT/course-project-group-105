package com.mg105.use_cases;

import com.mg105.entities.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class OpponentSetInteractor {
    private final GameState state;

    public OpponentSetInteractor(GameState gameState) {
        this.state = gameState;
    }

    /**
     * Checks if a given spot on the map contains an OpponentSet.
     * @return the OpponentSet if opponent is found, or null if it is not.
     */
    public static OpponentSet compareLists(Point coordinates, List<OpponentSet> opponents) {
        for (OpponentSet opponent : opponents) {
            if (coordinates.equals(opponent.getPosition())) {
                return opponent;
            }
        }
        return null;
    }

    /**
     * Checks if an OpponentSet is adjacent to the player character.
     * @return the OpponentSet if the opponent is found, or null if it is not.
     */
    public OpponentSet verifyOpponent() {

        Point charPosition = state.getWalkingCharacter().getCharPosition();
        List<OpponentSet> opponents = state.getCurrentRoom().getOpponents();
        List<Point> adjacents = AdjacentGetter.getAdjacents(charPosition);

        for (Point adjacentSpot : adjacents) {
            OpponentSet result = compareLists(adjacentSpot, opponents);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    /**
     * Sets the OpponentSet in the GameState to the one adjacent to the player.
     * @return whether there was an OpponentSet next to the player (true) or not (false).
     */
    public boolean setOpponentSet() {

        OpponentSet opponent = verifyOpponent();

        state.setCurrOpponent(opponent);
        return opponent != null;
    }
}
