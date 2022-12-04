package com.mg105.interface_adapters;

import com.mg105.use_cases.WalkVisInteractor;

/**
 * This class communicates with WalkVisInteractor, called on by WalkingMenu.
 * There is no presenter class. When this class is called on, WalkingMenu is closed.
 */
public class WalkVisController {

    private final WalkVisInteractor interactor;

    /**
     * Creates a new WalkVisController referring to a WalkVisInteractor.
     * @param interactor the WalkVisInteractor to be referred to.
     */
    public WalkVisController(WalkVisInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Calls on WalkVisInteractor to update the walking character sprite.
     * @param spriteName the name of the desired character sprite.
     */
    public void changePlayerSprite(String spriteName) {
        interactor.setWalkingSprite(spriteName);
    }
}
