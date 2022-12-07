package com.mg105.interface_adapters;

import com.mg105.entities.GameState;
import com.mg105.use_cases.RoomGetter;
import com.mg105.entities.items.MegaPotion;
import com.mg105.utils.PartyConstants;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Decides when to show game win screen
 */
public class WinDisplay implements PropertyChangeListener {
    private final Toggler toggler;
    private final RoomGetter roomGetter;
    private final GameState state;

    /**
     * Construction for the WinDisplay interface adapter
     *
     * @param toggler    toggles the win screen
     * @param roomGetter gets the current room of the player
     * @param state      the gamestate
     */
    public WinDisplay(Toggler toggler, RoomGetter roomGetter, GameState state) {
        this.toggler = toggler;
        this.roomGetter = roomGetter;
        this.state = state;
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
            toggler.toggle(Toggler.ToggleableComponent.WIN_MENU);

            MegaPotion potion = new MegaPotion();
            potion.consume(state, PartyConstants.ALL_PARTY_MEMBER_NAMES[0]);
            MegaPotion potion2 = new MegaPotion();
            potion2.consume(state, PartyConstants.ALL_PARTY_MEMBER_NAMES[1]);
            MegaPotion potion3 = new MegaPotion();
            potion3.consume(state, PartyConstants.ALL_PARTY_MEMBER_NAMES[2]);
            MegaPotion potion4 = new MegaPotion();
            potion4.consume(state, PartyConstants.ALL_PARTY_MEMBER_NAMES[3]);


        }
    }
}

