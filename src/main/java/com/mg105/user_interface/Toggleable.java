package com.mg105.user_interface;

import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

/**
 * A user interface component that can be toggled on or off depending on its visibility.
 */
public interface Toggleable {
    /**
     * Get the scene of this toggleable object.  It is this scene that will be displayed.
     *
     * @return the scene to be displayed.
     */
    @NotNull Scene getScene();

    /**
     * Set the visibility of this component.
     *
     * @param isVisible true if the Toggleable is now visible, false otherwise.  If false the Toggleable is expected
     *                  to do nothing on ANY user inputs.
     */
    void toggle(boolean isVisible);
}
