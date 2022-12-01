package com.mg105.use_cases;

import com.mg105.entities.GameState;

/**
 * This class interacts with the WalkingCharacter entity.
 * <p>
 * It is responsible for modifying the sprite attribute of WalkingCharacter.
 */
public class WalkVisInteractor {

    private final GameState state;

    public WalkVisInteractor(GameState state) {
        this.state = state;
    }

    /**
     * Updates the current WalkingCharacter's sprite attribute.
     * @param spriteName the name of the desired character sprite to be used.
     */
    public void setWalkingSprite(String spriteName) {
        state.getWalkingCharacter().setSpriteName(spriteName);
    }
}
