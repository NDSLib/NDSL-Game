package com.ndsl.bun133.game.map.gen;

import com.ndsl.bun133.game.map.chunk.Chunk;

public interface IGenerator {
    Chunk gen(Chunk chunk);
}
