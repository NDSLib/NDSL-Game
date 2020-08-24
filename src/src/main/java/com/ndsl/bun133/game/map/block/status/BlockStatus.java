package com.ndsl.bun133.game.map.block.status;

import com.ndsl.bun133.game.map.block.animator.BlockAnimator;
import com.ndsl.bun133.game.map.block.onMapBlock;

public class BlockStatus {
    public onMapBlock Block;

    public BlockStatus(onMapBlock block){
        this.Block=block;
        this.animator=new BlockAnimator(this,block.block.BlockImage);
    }

    public NBT nbt=new NBT();

    public BlockAnimator animator;

    public BlockAnimator getAnimator(){
        return animator;
    }
}