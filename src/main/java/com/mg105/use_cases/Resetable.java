package com.mg105.use_cases;

/**
 * A use case that needs to be reset when the game restarts (regardless of win or loss).
 */
public interface Resetable {
    /**
     * Reset this component as if the game just started.
     */
    void reset();
}
