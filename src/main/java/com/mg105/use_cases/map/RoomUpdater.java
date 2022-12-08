package com.mg105.use_cases.map;

import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * RoomUpdater updates any components that need updating when the state of the current room changes.
 * <p>
 * One example of a component that might need updating is the GUI.
 * <p>
 * RoomUpdater makes use of the Java9+ Observable pattern.
 */
public class RoomUpdater implements RoomUpdaterInterface {
    private final @NotNull PropertyChangeSupport observable;

    /**
     * Create a new RoomUpdater.
     */
    public RoomUpdater() {
        observable = new PropertyChangeSupport(this);
    }

    /**
     * Trigger an update on all the components that need updating on a room state change.
     */
    @Override
    public void updateRoom() {
        observable.firePropertyChange("", null, null);
    }

    /**
     * Add a new observer to the RoomUpdater.
     *
     * @param observer an observer that requires notification of room changes.
     */
    public void addObserver(@NotNull PropertyChangeListener observer) {
        observable.addPropertyChangeListener(observer);
    }

    /**
     * Remove an observer from the RoomUpdater.
     *
     * @param observer an observer that no longer requires notification of room changes.
     */
    public void removeObserver(@NotNull PropertyChangeListener observer) {
        observable.removePropertyChangeListener(observer);
    }
}
