package com.mg105.user_interface;

import com.mg105.interface_adapters.Toggler;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Stack;

/**
 * SceneController acts as the central coordination mechanism for the user interface.
 */
public class SceneController implements Toggler {
    private final @NotNull Stage primaryStage;

    private final @NotNull Map<ToggleableComponent, Toggleable> components;
    private final @NotNull ToggleableComponent defaultComponent;

    private final @NotNull Stack<ToggleableComponent> activeComponents;

    /**
     * Create a new scene controller
     *
     * @param primaryStage     the JavaFX stage to draw on.
     * @param components       all the possible components in the system.  Components should contain an entry for each
     *                         item in the ToggleableComponent enum.
     * @param defaultComponent the component that will be shown if there's nothing else to show.
     */
    public SceneController(@NotNull Stage primaryStage,
                           @NotNull Map<ToggleableComponent, Toggleable> components,
                           @NotNull ToggleableComponent defaultComponent) {
        this.primaryStage = primaryStage;
        this.defaultComponent = defaultComponent;
        this.components = components;

        activeComponents = new Stack<>();
        activeComponents.push(defaultComponent);
    }

    /**
     * Toggle the component. i.e. switch it between invisible and visible.
     *
     * @param component the component to toggle.
     */
    @Override
    public void toggle(@NotNull ToggleableComponent component) {
        if (activeComponents.peek().equals(component)) { // if the component is currently being displayed
            components.get(component).toggle(false);
            activeComponents.pop();
        } else if (!activeComponents.remove(component)) { // if the component is not currently in the stack
            components.get(activeComponents.peek()).toggle(false);
            activeComponents.push(component);
        }

        if (activeComponents.isEmpty()) {
            activeComponents.push(defaultComponent);
        }

        components.get(activeComponents.peek()).toggle(true);
        primaryStage.setScene(components.get(activeComponents.peek()).getScene());
    }

    /**
     * Get the current visible component.
     *
     * @return the current visible component.
     */
    @Override
    public @NotNull ToggleableComponent getCurrentComponent() {
        return activeComponents.peek();
    }
}
