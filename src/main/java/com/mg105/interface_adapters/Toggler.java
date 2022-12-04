package com.mg105.interface_adapters;

import org.jetbrains.annotations.NotNull;

/**
 * An interface for an object that can toggle the visibility of various user interface components.
 */
public interface Toggler {
    /**
     * Toggle the component. i.e. switch it between invisible and visible.
     *
     * @param component the component to toggle.
     */
    void toggle(@NotNull ToggleableComponent component);

    /**
     * Get the current visible component.
     *
     * @return the current visible component.
     */
    @NotNull ToggleableComponent getCurrentComponent();

    /**
     * All the possible components that could theoretically be toggled.
     */
    enum ToggleableComponent {
        MAIN_MENU,
        MAP,
        INVENTORY,
        BATTLE,
        TUTORIAL,
        WALK_MENU,
        LOSE_MENU
    }
}
