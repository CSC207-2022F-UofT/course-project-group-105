package com.mg105.use_cases.set_up.state_setter;

import com.mg105.entities.GameState;

/**
 * This class should only be called in the "main" file. It has a method that is used to set information about the
 * GameState
 * <p>
 * This is essentially the facade of the StateSetters class that fills the GameState with the data it needs to when
 * the game starts
 */

public class GameStateSetter implements StateSetter {

    // Right now there is only one other class that implements StateSetter so the setters list will only have one object
    // in
    // This kinda makes the facade useless. But if we had more time we would have a larger database and thus
    // we would be loading more data and more types of data that would each need its own StateSetter

    // For example if we had inventory data in the database, that data would need to leaded and set when the game
    // starts so thus it would need its own StateSetter
    private final StateSetter[] setters;

    public GameStateSetter(StateSetter[] setters) {
        this.setters = setters;
    }

    @Override
    public void setState(GameState state) {
        for (StateSetter setter : this.setters) {

            setter.setState(state);
        }

    }
}
