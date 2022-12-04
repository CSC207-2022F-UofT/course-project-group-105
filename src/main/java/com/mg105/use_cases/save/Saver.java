package com.mg105.use_cases.save;

import org.jetbrains.annotations.NotNull;

/**
 * This classes only job is to save data about the state of the game in some sort of "database"
 * This class is the Facade class that uses others savers to save info about the game
 */

public class Saver implements Save {

    @NotNull private final Save[] saver;

    /**
     * Creates a new instance of saver
     * @param savers a group of object that can be used to save piece of the game state's data
     */
    public Saver(@NotNull Save[] savers) {
        this.saver = savers;
    }

    // In the future (if we had more time) we would save more data and the facade pattern would be more useful for
    // saving every type of data appropriatley (i.e. save state of inventory,
    // the amount of battles won, number of losses and more)
    // Thus we would have more savers

    // Right now the only class that implments saver besides this one is the PartySaver

    /**
     * Save the current state of the game to a "database"
     */

    @Override
    public void save() {
        for (Save saver : this.saver) {
            saver.save();
        }
    }
}
