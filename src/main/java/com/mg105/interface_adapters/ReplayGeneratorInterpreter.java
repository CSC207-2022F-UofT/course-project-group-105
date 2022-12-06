package com.mg105.interface_adapters;

import com.mg105.use_cases.map.ReplayGenerator;
import org.jetbrains.annotations.NotNull;

/**
 * ReplayGenerator asks as a thin interface adapter whose sole purpose is to maintain clean architecture.
 * All it does is pass control on to the use case component of game replay generation.
 */
public class ReplayGeneratorInterpreter {
    private final @NotNull ReplayGenerator replayGenerator;

    /**
     * @param replayGenerator the replayGenerator that would be used to replay the game.
     */
    public ReplayGeneratorInterpreter(@NotNull ReplayGenerator replayGenerator) {
        this.replayGenerator = replayGenerator;
    }

    /**
     * Replay the game.
     */
    public void replayGenerateMap() {
        replayGenerator.replay();
    }
}

