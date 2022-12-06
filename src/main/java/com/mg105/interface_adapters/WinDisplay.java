package com.mg105.interface_adapters;

import com.mg105.use_cases.map.RoomGetter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Decides when to show game win screen
 */
public class WinDisplay implements PropertyChangeListener {
    private final Toggler toggler;
    private final RoomGetter roomGetter;

    /**
     * Construction for the WinDisplay interface adapter
     * @param toggler toggles the win screen
     * @param roomGetter gets the current room of the player
     */
    public WinDisplay(Toggler toggler, RoomGetter roomGetter){
        this.toggler = toggler;
        this.roomGetter = roomGetter;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (roomGetter.isFinalRoom()){
            toggler.toggle(Toggler.ToggleableComponent.WIN_MENU);
        }
    }
}
