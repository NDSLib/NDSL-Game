package com.ndsl.bun133.game.map.chunk;

import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.game.map.chunk.block.onMapBlock;
import com.ndsl.bun133.game.map.pos.ChunkPos;
import com.ndsl.bun133.game.map.pos.onMapBlockPos;
import com.ndsl.bun133.game.map.pos.onMapRect;
import com.ndsl.bun133.game.register.Blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class Chunk {
    public Map<onMapBlockPos, onMapBlock> BlockMap=new HashMap<>();

    public ChunkPos pos;
    public com.ndsl.bun133.game.map.Map map;

    public Chunk(ChunkPos pos, com.ndsl.bun133.game.map.Map map){
        this.pos=pos;
        this.map=map;
        for(onMapBlockPos block_pos:allBlockPos()){
            BlockMap.put(block_pos,new onMapBlock(Blocks.TEST_BLOCK,block_pos,map));
        }
    }

    public List<onMapBlockPos> allBlockPos(){
        return getRect().getAll(map);
    }

    public onMapRect getRect(){
        return new onMapRect(getLeft_up(),getRight_down());
    }

    public onMapBlockPos getLeft_up(){
        return getCenter().add(-GameMain.CHUNK_SIZE_HALF,-GameMain.CHUNK_SIZE_HALF);
    }

    public onMapBlockPos getRight_down(){
        return getCenter().add(GameMain.CHUNK_SIZE_HALF,GameMain.CHUNK_SIZE_HALF);
    }

    public onMapBlockPos getCenter(){
        return new onMapBlockPos(pos.chunk_x*GameMain.CHUNK_SIZE+GameMain.CHUNK_SIZE_HALF,pos.chunk_y*GameMain.CHUNK_SIZE+GameMain.CHUNK_SIZE_HALF,map);
    }
}
