package com.mg105.interface_adapters.map;

import com.mg105.use_cases.map.MapGeneratorInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapGeneratorInterpreterTest {
    @Test
    void passthrough() {
        CountingMapGenerator generator = new CountingMapGenerator();
        MapGeneratorInterpreter interpreter = new MapGeneratorInterpreter(generator);
        assertEquals(0, generator.getCount());
        interpreter.generateMap();
        assertEquals(1, generator.getCount());
    }

    private static class CountingMapGenerator implements MapGeneratorInterface {
        private int count = 0;

        public int getCount() {
            return count;
        }

        @Override
        public void generateMap() {
            count += 1;
        }
    }
}
