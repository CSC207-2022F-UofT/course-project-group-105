package com.mg105.interface_adapters;

import com.mg105.use_cases.ReplayGenerator;
import com.mg105.use_cases.map.RoomGetterInterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Decides when to show game win screen
 */
public class WinDisplay implements PropertyChangeListener {
    private final Toggler toggler;
    private final RoomGetterInterface roomGetter;
    private final ReplayGenerator replayGenerator;

    /**
     * Construction for the WinDisplay interface adapter
     *
     * @param toggler         toggles the win screen
     * @param roomGetter      gets the current room of the player
     * @param replayGenerator generates a replay
     */
    public WinDisplay(Toggler toggler, RoomGetterInterface roomGetter, ReplayGenerator replayGenerator) {
        this.toggler = toggler;
        this.roomGetter = roomGetter;
        this.replayGenerator = replayGenerator;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (roomGetter.isFinalRoom()) {
            replayGenerator.reviveCharacters();
            toggler.toggle(Toggler.ToggleableComponent.WIN_MENU);
        }
    }
}

