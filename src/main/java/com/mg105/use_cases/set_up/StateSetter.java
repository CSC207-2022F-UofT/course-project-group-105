package com.mg105.use_cases.set_up;

import com.mg105.entities.GameState;

/**
 * This interface is for classes that help set information in GameState when a game has first started
 */

public interface StateSetter {

    /**
     * Sets some aspect of the gameState in this method
     */

    void setState(GameState state);
}
