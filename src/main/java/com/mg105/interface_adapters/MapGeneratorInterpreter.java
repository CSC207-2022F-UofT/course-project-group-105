package com.mg105.interface_adapters;

import com.mg105.use_cases.MapGenerator;
import org.jetbrains.annotations.NotNull;

/**
 * MapGeneratorInterpreter asks as a thin interface adapter whose sole purpose is to maintain clean architecture.
 * All it does is pass control on to the use case component of map generation.
 */
public class MapGeneratorInterpreter {
    private final MapGenerator generator;

    /**
     * Create a new MapGeneratorInterpreter.
     *
     * @param generator the generator that will be used to generate a new map.
     */
    public MapGeneratorInterpreter(@NotNull MapGenerator generator) {
        this.generator = generator;
    }

    /**
     * Generate (or re-generate) the map.
     */
    public void generateMap() {
        generator.generateMap();
    }
}
