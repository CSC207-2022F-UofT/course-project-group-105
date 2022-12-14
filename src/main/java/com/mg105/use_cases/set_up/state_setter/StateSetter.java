package com.mg105.use_cases.set_up.state_setter;

import com.mg105.entities.GameState;

/**
 * This interface is for classes that help set information in GameState when a game has first started
 */

public interface StateSetter {

    /**
     * Sets some aspect of the gameState in this method
     *
     * @param state the game state to act on.
     */
    void setState(GameState state);
}
