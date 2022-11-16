package com.mg105;

import com.mg105.entities.GameState;
import com.mg105.use_cases.MapGenerator;

/**
 * The main class that sets up the clean architecture mountain group 105 game!
 */
public class Main {
    /**
     * The main method.  It all begins here.
     *
     * @param args the commandline arguments.  We do not use it.
     */
    public static void main(String[] args) {
        // Setup the initial entities
        GameState state = new GameState();

        // Setup the initial use cases
        MapGenerator mapGenerator = new MapGenerator(state);
    }
}
