package com.mg105.interface_adapters;

import com.mg105.use_cases.ReplayGenerator;
import org.jetbrains.annotations.NotNull;

public class ReplayGeneratorInterpreter {
    private final @NotNull ReplayGenerator replayGenerator;

    public ReplayGeneratorInterpreter(@NotNull ReplayGenerator replayGenerator) {
        this.replayGenerator = replayGenerator;
    }

    public void replayGenerateMap() {
        replayGenerator.replay();
    }
}

