package com.mg105.interface_adapters;

import com.mg105.use_cases.RoomGetter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WinDisplay implements PropertyChangeListener {
    private final Toggler toggler;
    private final RoomGetter roomGetter;

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
